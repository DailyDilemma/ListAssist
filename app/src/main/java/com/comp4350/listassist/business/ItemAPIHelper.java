package com.comp4350.listassist.business;

import android.content.Context;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.comp4350.listassist.R;
import com.comp4350.listassist.objects.LAList;
import com.comp4350.listassist.objects.ShoppingListItem;
import com.comp4350.listassist.objects.SuggestedListItem;
import com.comp4350.listassist.presentation.ViewActivity;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * Created by Daniel on 3/17/2016 for app.
 */
public class ItemAPIHelper extends AsyncTask<String, LAList, Boolean> {
    /*
    * Use:
    *   new ItemAPIHelper(ShoppingListItem new_item).execute({listId});
    *       - add item ShoppingListItem to listId
    *
    *   new ItemAPIHelper(Activity context).execute();
    *       - refresh list information and fill out R.id.item_table
    *
    *   new ItemAPIHelper(Activity context).execute("check", {itemId});
    *       - sets item as checked in context list
    *
    *   new ItemAPIHelper(Activity context).execute("delete", {itemId});
    *       - deletes item from context list
    * */
    private TableLayout item_table;
    private ViewActivity context;
    private ShoppingListItem new_item;
    private SuggestedListItem suggested_item;

    public ItemAPIHelper() {
        this.context = null;
        this.item_table = null;
        this.new_item = null;
    }

    public ItemAPIHelper(ViewActivity context) {
        this();
        this.context = context;
        this.item_table = (TableLayout)context.findViewById(R.id.item_table);
    }

    public ItemAPIHelper(ShoppingListItem new_item) {
        this();
        this.new_item = new_item;
    }

    public ItemAPIHelper(SuggestedListItem suggested_item) {
        this();
        this.suggested_item = suggested_item;
    }

    // doInBackground is the function being called
    @Override
    protected Boolean doInBackground(String... params) {
        boolean success = false;

        try {
            if (params.length == 0) {
                if(context != null) {
                    // Get List
                    String listId = context.getIntent().getStringExtra("listId");
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    LAList LAList = restTemplate.getForObject("http://ec2-52-36-187-54.us-west-2.compute.amazonaws.com:8080/api/Lists/" + listId, LAList.class);

                    publishProgress(LAList);

                    success = true;
                } else if (new_item != null) {
                    String url = "http://ec2-52-36-187-54.us-west-2.compute.amazonaws.com:8080/api/ListItems";

                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                    restTemplate.postForObject(url, new_item, Integer.class);

                    this.new_item = null;
                }


            } else if (params.length == 2) {
                if(context != null) {
                    String url;
                    RestTemplate restTemplate;
                    String listId = context.getIntent().getStringExtra("listId");
                    String itemId;
                    int response;

                    switch (params[0]) {
                        case "check":
                            itemId = params[1];
                            url = "http://ec2-52-36-187-54.us-west-2.compute.amazonaws.com:8080/api/ListItems/list/" + listId + "/item/" + itemId;
                            restTemplate = new RestTemplate();
                            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                            restTemplate.put(url, null);

                            break;
                        case "delete":
                            itemId = params[1];
                            url = "http://ec2-52-36-187-54.us-west-2.compute.amazonaws.com:8080/api/ListItems/list/" + listId + "/item/" + itemId;
                            restTemplate = new RestTemplate();
                            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                            restTemplate.delete(url);
                            break;
                        case "suggest":
                            itemId = params[1];
                            url = "http://ec2-52-36-187-54.us-west-2.compute.amazonaws.com:8080/api/AcceptSuggestion/" + itemId;
                            restTemplate = new RestTemplate();

                            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                            response = restTemplate.postForObject(url, null, Integer.class);

                            if (response < 300 && response > 199) {
                                success = true;
                            } else {
                                Log.e("ItemAPIHelper", response + ": " + url);
                            }

                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            Log.e("ItemAPIHelper", e.getMessage(), e);
        }

        return success;
    }

    // When getting all lists, update progress with single list and add it to the app
    @Override
    protected void onProgressUpdate(LAList... progress) {
        // Normally what you would do here is make a call to your list adapter to give it the
        // updated information now that is has returned from the thread. For now you can check
        // logcat to see the result of println messages if you want to verify there is data
        // in the classes
        if (progress.length == 1) {
            set_list(progress[0]);
        }
    }

    // onPostExecute is what happens when the thread is complete. The result of 'doInBackground'
    // is returned as an argument to this method
    @Override
    protected void onPostExecute(Boolean success) {
        color_table();

        boolean _success = success;
    }

    // View changing methods
    private void color_table() {
        if(item_table != null) {
            for (int i = 0; i < item_table.getChildCount(); i++) {
                TableRow curr_row = (TableRow) item_table.getChildAt(i);

                if (i % 2 == 0) {
                    curr_row.setBackground(item_table.getContext().getDrawable(R.drawable.banner_even));
                } else {
                    curr_row.setBackgroundColor(ContextCompat.getColor(item_table.getContext(), R.color.colorPrimaryLight));
                }
            }
        }
    }

    private void set_list(LAList list) {
        // Dynamically add lists
        if(context != null) {
            if( list.getId() != null ) {

                List<SuggestedListItem> suggested = list.getSuggestedListItems();
                Button suggestedItem = (Button) context.findViewById(R.id.suggested_item);

                //generateTestSuggestions(suggested);

                if(suggested.isEmpty())
                {
                    suggestedItem.setText("No suggestions available.");
                }
                else
                {
                    int index = getRandomIndex(suggested.size());
                    suggestedItem.setText("Suggested for you: " + suggested.get(index).getDescription());
                    suggestedItem.setTag(suggested.get(index).getId());
                }

                ((TextView) context.findViewById(R.id.list_name)).setText(list.getName());

                for (ShoppingListItem item : list.getShoppingListItems()) {
                    if(item.getId() != null && item.getDescription() != null) {
                        LayoutInflater inflater = (LayoutInflater) context.getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);

                        View list_item = inflater.inflate(
                                R.layout.list_item, item_table, false
                        );

                        String msg = item.getDescription() + " has ID = " + item.getId().toString();
                        Log.i("ItemAPIHelper", msg);
                        list_item.setTag(item.getId());

                        CheckBox tv = (CheckBox) list_item.findViewById(R.id.item);
                        tv.setText(item.getDescription());

                        if (item.getChecked()) {
                            tv.setChecked(item.getChecked());
                            int pflag =  tv.getPaintFlags();

                            if(((pflag | Paint.STRIKE_THRU_TEXT_FLAG) - pflag) != 0) {
                                // text is not striked, add it
                                tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            }
                            tv.setClickable(false);

                            item_table.addView(list_item, item_table.getChildCount());
                        } else {
                            item_table.addView(list_item, 0);
                        }


                    } else {
                        String desc = item.getDescription();
                        Log.e("ItemAPIHelper", "Failure on item " + desc);
                    }
                }
            } else {
                Log.e("ItemAPIHelper", "Failure to get list id");
            }
        }
    }

    private int getRandomIndex(int max)
    {
        Random rand = new Random();
        int index = rand.nextInt(max + 1) % max;

        return index;
    }

    private void generateTestSuggestions(List<SuggestedListItem> suggested)
    {
        suggested.add(new SuggestedListItem("Water", 999, 999));
        suggested.add(new SuggestedListItem("Air", 997, 999));
        suggested.add(new SuggestedListItem("Sunlight", 899, 999));
        suggested.add(new SuggestedListItem("Food", 998, 999));
    }
}
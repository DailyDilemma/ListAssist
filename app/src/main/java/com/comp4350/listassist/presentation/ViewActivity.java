package com.comp4350.listassist.presentation;

/**
 * Created by Daniel on 3/13/2016 for ListAssist.
 */

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.comp4350.listassist.R;
import com.comp4350.listassist.objects.ShoppingList;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class ViewActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        Intent intent = getIntent();
        String list_name = intent.getStringExtra("name");
        setContentView(R.layout.view_list);

        ((TextView)this.findViewById(R.id.list_name)).setText(list_name);

        //TODO: Add api call to get list items

        // Dynamically add list items
        ViewGroup item_table = (ViewGroup)findViewById(R.id.item_table);
        for(int i = 0; i < 3; i++) {
            View item_row_entry = getLayoutInflater().inflate(
                    R.layout.list_item, item_table, false
            );

            TextView tv = (TextView)item_row_entry.findViewById(R.id.item);

            tv.setText("Test item " + i);

            if(i %2 == 0){
                Drawable background = ContextCompat.getDrawable(this, R.drawable.banner_even);
                item_row_entry.setBackground(background);
            }

            item_table.addView(item_row_entry, i);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }

    public void delete_item(View view) {
        //TODO: Remove an item from the list with api call, refresh list
    }

    public void check_item(View view) {
        // Check off an item, send to bottom of list
        TableRow tr = (TableRow)view.getParent();
        TableLayout tl = (TableLayout)this.findViewById(R.id.item_table);
        CheckBox c_view = (CheckBox)view;

        //TODO: Add api call to check item and add last bought interval, refresh list
        int pflag =  c_view.getPaintFlags();
        if(((pflag | Paint.STRIKE_THRU_TEXT_FLAG) - pflag) == 0) {
            // text is already striked, remove it
            c_view.setPaintFlags(c_view.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        } else {
            // text is not striked, add it
            c_view.setPaintFlags(c_view.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tl.removeView(tr);
            tl.addView(tr);
        }
    }

    public void add_item(View view) {
        // Open a list
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        AddingDialog list_dialog = AddingDialog.newInstance("New Item", "item");
        list_dialog.show(ft, "dialog");
    }

    private void color_table() {
        ViewGroup list_table = (ViewGroup)findViewById(R.id.item_table);

        for(int i = 0; i < list_table.getChildCount(); i++) {
            TableRow curr_row = (TableRow)list_table.getChildAt(i);

            if(i % 2 == 0) {
                curr_row.setBackground(getDrawable(R.drawable.banner_even));
            } else {
                curr_row.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryLight));
            }
        }
    }

    private void append_list(ShoppingList item) {
        // Dynamically add lists
        ViewGroup list_table = (ViewGroup)findViewById(R.id.item_table);

        View list_row_entry = getLayoutInflater().inflate(
                R.layout.list_item, list_table, false
        );

        TextView tv = (TextView)list_row_entry.findViewById(R.id.list_name);

        tv.setText(item.getName());

        list_table.addView(list_row_entry);
    }

    // This class contains methods that run on a separate thread
    protected class ItemAPIHelper extends AsyncTask<String, ShoppingList, Boolean> {
        // doInBackground is the function being called
        @Override
        protected Boolean doInBackground(String... params) {
            boolean success = false;

            try {
                if(params.length == 0) {
                    // Get all lists: use progress to
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    ShoppingList[] shopLists = restTemplate.getForObject("http://ec2-52-36-187-54.us-west-2.compute.amazonaws.com:8080/api/Lists", ShoppingList[].class);

                    for(ShoppingList curr_list : shopLists) {
                        publishProgress(curr_list);
                    }

                    success = true;
                } else if(params.length == 2) {
                    int id;
                    switch (params[0]) {
                        case "delete":
                            id = Integer.parseInt(params[1]);
                            break;
                        case "get":
                            id = Integer.parseInt(params[1]);
                            String url = "http://ec2-52-36-187-54.us-west-2.compute.amazonaws.com:8080/api/Lists/" + id;
                            RestTemplate restTemplate = new RestTemplate();
                            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                            ShoppingList shopList = restTemplate.getForObject(url, ShoppingList.class);
                            publishProgress(shopList);
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return success;
        }

        // When getting all lists, update progress with single list and add it to the app
        @Override
        protected void onProgressUpdate(ShoppingList... progress) {
            // Normally what you would do here is make a call to your list adapter to give it the
            // updated information now that is has returned from the thread. For now you can check
            // logcat to see the result of println messages if you want to verify there is data
            // in the classes
            if(progress.length == 1) {
                append_list(progress[0]);
            }
        }

        // onPostExecute is what happens when the thread is complete. The result of 'doInBackground'
        // is returned as an argument to this method
        @Override
        protected void onPostExecute(Boolean success) {
            color_table();

            boolean _success = success;
        }

    }
}

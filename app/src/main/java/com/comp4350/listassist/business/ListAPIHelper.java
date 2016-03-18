package com.comp4350.listassist.business;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.comp4350.listassist.R;
import com.comp4350.listassist.objects.ShoppingList;
import com.comp4350.listassist.presentation.MainActivity;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Daniel on 3/17/2016 for app.
 */
public class ListAPIHelper extends AsyncTask<String, ShoppingList, Boolean> {
    private TableLayout list_table;
    private Context curr_context;

    public ListAPIHelper() {
        // Used for make when context of app isn't needed
        list_table = null;
        curr_context = null;
    }

    public ListAPIHelper(TableLayout list_table) {
        // Used for getting, all returned objects are put in the tablelayout passed in
        this.list_table = list_table;
        this.curr_context = list_table.getContext();
    }

    // doInBackground is the function being called
    @Override
    protected Boolean doInBackground(String... params) {
        boolean success = false;

        try {
            if (params.length == 0) {
                // Get all lists: use progress to push lists out
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ShoppingList[] shopLists = restTemplate.getForObject("http://ec2-52-36-187-54.us-west-2.compute.amazonaws.com:8080/api/Lists", ShoppingList[].class);

                for (ShoppingList curr_list : shopLists) {
                    publishProgress(curr_list);
                }

                success = true;
            } else if (params.length == 2) {
                String url;
                RestTemplate restTemplate;
                ShoppingList shopList;

                switch (params[0]) {
                    case "delete":
                        String id = params[1];
                        url = "http://ec2-52-36-187-54.us-west-2.compute.amazonaws.com:8080/api/Lists?listId=" + id;
                        restTemplate = new RestTemplate();
                        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                        restTemplate.delete(url);
                        break;
                    case "make":
                        String new_list_name = params[1];
                        url = "http://ec2-52-36-187-54.us-west-2.compute.amazonaws.com:8080/api/Lists?listName=" + new_list_name;

                        restTemplate = new RestTemplate();
                        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                        int response = restTemplate.postForObject(url, null, Integer.class);

                        if(response < 300 && response > 199) {
                            success = true;
                        } else {
                            Log.e("ListAPIHelper", response + ": " + url);
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            Log.e("ListAPIHelper", e.getMessage(), e);
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
        if (progress.length == 1) {
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

    // View changing methods
    private void color_table() {
        if(list_table != null) {
            for (int i = 0; i < list_table.getChildCount(); i++) {
                TableRow curr_row = (TableRow) list_table.getChildAt(i);

                if (i % 2 == 0) {
                    curr_row.setBackground(list_table.getContext().getDrawable(R.drawable.banner_even));
                } else {
                    curr_row.setBackgroundColor(ContextCompat.getColor(list_table.getContext(), R.color.colorPrimaryLight));
                }
            }
        }
    }

    private void append_list(ShoppingList item) {
        // Dynamically add lists
        if(curr_context != null) {
            LayoutInflater inflater = (LayoutInflater) curr_context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);

            View list_row_entry = inflater.inflate(
                    R.layout.list_row_entry, list_table, false
            );

            TextView tv = (TextView) list_row_entry.findViewById(R.id.list_name);

            tv.setText(item.getName());

            list_table.addView(list_row_entry);
        }
    }
}
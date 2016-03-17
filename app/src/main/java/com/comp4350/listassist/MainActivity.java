package com.comp4350.listassist;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.AsyncTask;
import android.util.Log;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        setContentView(R.layout.app_main);

        // Calls the WebAPI on a separate thread to populate the initial list
        new HttpRequestTask().execute();

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

    // Button handlers
    public void add_list(View view) {
        // Add a list
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        AddingDialog list_dialog = AddingDialog.newInstance("New List", "list");
        list_dialog.show(ft, "dialog");
    }

    public void open_list(View view) {
        // Open a list
        Intent list = new Intent(this, ViewActivity.class);
        TextView list_name = (TextView)((ViewGroup) view.getParent()).findViewById(R.id.list_name);
        list.putExtra("name", list_name.getText().toString());
        startActivity(list);
    }

    public void delete_list(View view) {
        //TODO: Remove list with api call, refresh lists
        TextView list_name = (TextView)((ViewGroup) view.getParent()).findViewById(R.id.list_name);
    }

    private void append_list(ShoppingList item) {
        // Dynamically add lists
        ViewGroup list_table = (ViewGroup)findViewById(R.id.list_table);

        View list_row_entry = getLayoutInflater().inflate(
                R.layout.list_row_entry, list_table, false
        );

        TextView tv = (TextView)list_row_entry.findViewById(R.id.list_name);

        tv.setText(item.getName());

        list_table.addView(list_row_entry);
    }

    // This class contains methods that run on a separate thread
    private class HttpRequestTask extends AsyncTask<String, ShoppingList, Boolean> {
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
            boolean _success = success;
        }

    }
}

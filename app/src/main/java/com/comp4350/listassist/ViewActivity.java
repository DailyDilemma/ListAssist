package com.comp4350.listassist;

/**
 * Created by Daniel on 3/13/2016.
 */

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        setContentView(R.layout.view_list);

        // Dynamically add lists
        ViewGroup item_table = (ViewGroup)findViewById(R.id.item_table);
        for(int i = 0; i < 3; i++) {
            View item_row_entry = getLayoutInflater().inflate(
                    R.layout.list_item, item_table, false
            );

            TextView tv = (TextView)item_row_entry.findViewById(R.id.item);
            // Get list names from API
            tv.setText("Test item " + i);

            if(i %2 == 0){
                int color = ContextCompat.getColor(this, R.color.colorEven);
                item_row_entry.setBackgroundColor(color);
            } else {
                int color = ContextCompat.getColor(this, R.color.colorOdd);
                item_row_entry.setBackgroundColor(color);
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
        // Open a list
    }

    public void check_item(View view) {
        // Open a list
    }

    public void add_item(View view) {
        // Open a list
    }
}

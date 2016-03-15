package com.comp4350.listassist;

/**
 * Created by Daniel on 3/13/2016 for ListAssist.
 */

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
        Intent intent = getIntent();
        String list_name = intent.getStringExtra("name");
        setContentView(R.layout.view_list);

        ((TextView)this.findViewById(R.id.list_name)).setText(list_name);

        // Dynamically add list items
        ViewGroup item_table = (ViewGroup)findViewById(R.id.item_table);
        for(int i = 0; i < 3; i++) {
            View item_row_entry = getLayoutInflater().inflate(
                    R.layout.list_item, item_table, false
            );

            TextView tv = (TextView)item_row_entry.findViewById(R.id.item);
            // Get list items from API
            tv.setText("Test item " + i);

            if(i %2 == 0){
                Drawable background = ContextCompat.getDrawable(this, R.drawable.banner_even);
                item_row_entry.setBackground(background);
            } else {
                Drawable background = ContextCompat.getDrawable(this, R.drawable.banner_odd);
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
        // Open a list
    }

    public void check_item(View view) {
        // Open a list
    }

    public void add_item(View view) {
        // Open a list
    }
}

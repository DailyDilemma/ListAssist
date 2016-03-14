package com.comp4350.listassist;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        setContentView(R.layout.app_main);

        // Dynamically add list items
        ViewGroup list_table = (ViewGroup)findViewById(R.id.list_table);
        for(int i = 0; i < 3; i++) {
            View list_row_entry = getLayoutInflater().inflate(
                    R.layout.list_row_entry, list_table, false
            );

            TextView tv = (TextView)list_row_entry.findViewById(R.id.listName);
            // Get list names from API
            tv.setText("Test list " + i);

            if(i %2 == 0){
                int color = ContextCompat.getColor(this, R.color.colorEven);
                list_row_entry.setBackgroundColor(color);
            } else {
                int color = ContextCompat.getColor(this, R.color.colorOdd);
                list_row_entry.setBackgroundColor(color);
            }

            list_table.addView(list_row_entry, i);
        }

        // Add button listeners
        final Button o_button = (Button) findViewById(R.id.open_button);
        o_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open list: use parent object to get list name / list id
            }
        });

        final Button d_button = (Button) findViewById(R.id.delete_button);
        d_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Delete list
            }
        });

        final ImageButton nl_button = (ImageButton) findViewById(R.id.new_list_button);
        nl_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Add new list
            }
        });
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
}

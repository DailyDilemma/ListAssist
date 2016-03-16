package com.comp4350.listassist;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        setContentView(R.layout.app_main);

        //TODO: Get list of lists from api call

        // Dynamically add lists
        ViewGroup list_table = (ViewGroup)findViewById(R.id.list_table);
        for(int i = 0; i < 3; i++) {
            View list_row_entry = getLayoutInflater().inflate(
                    R.layout.list_row_entry, list_table, false
            );

            TextView tv = (TextView)list_row_entry.findViewById(R.id.list_name);

            tv.setText("Test list " + i);

            if(i %2 == 0){
                Drawable background = ContextCompat.getDrawable(this, R.drawable.banner_even);
                list_row_entry.setBackground(background);
            }

            list_table.addView(list_row_entry, i);
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


}

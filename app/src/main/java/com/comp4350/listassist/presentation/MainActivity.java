package com.comp4350.listassist.presentation;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.comp4350.listassist.R;
import com.comp4350.listassist.business.ListAPIHelper;

public class MainActivity extends Activity {
    public static TableLayout list_table;
    private static MainActivity self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        setContentView(R.layout.app_main);

        list_table = (TableLayout)findViewById(R.id.list_table);

        // Calls the WebAPI on a separate thread to populate the initial list
        new ListAPIHelper(this).execute();
        self = this;
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
        Object tag = ((ViewGroup) view.getParent()).getTag();

        if(tag != null) {
            String id = tag.toString();
            Intent list = new Intent(this, ViewActivity.class);
            TextView list_name = (TextView)((ViewGroup) view.getParent()).findViewById(R.id.list_name);
            list.putExtra("name", list_name.getText().toString());
            list.putExtra("listId", id);

            startActivity(list);
        } else {
            Log.e("MainActivity", "Failure to find id for opening list.");
        }
    }

    public void delete_list(View view) {
        Object tag = ((ViewGroup) view.getParent()).getTag();

        if( tag != null) {
            String id = tag.toString();
            new ListAPIHelper(this).execute("delete", id);
        } else {
            Log.e("MainActivity", "Failure to find id for deleting list.");
        }

        refresh_table();
    }

    public void refresh_list(View view) {
        MainActivity.refresh_table();
    }

    public static void refresh_table() {
        list_table.removeAllViews();

        new ListAPIHelper(self).execute();
    }
}

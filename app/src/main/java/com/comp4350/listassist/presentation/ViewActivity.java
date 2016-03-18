package com.comp4350.listassist.presentation;

/**
 * Created by Daniel on 3/13/2016 for ListAssist.
 */

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.comp4350.listassist.R;
import com.comp4350.listassist.business.ItemAPIHelper;

public class ViewActivity extends Activity {
    private static ViewActivity self;
    private static TableLayout item_table;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        Intent intent = getIntent();
        String list_name = intent.getStringExtra("name");
        setContentView(R.layout.view_list);

        self = this;
        item_table = (TableLayout)this.findViewById(R.id.item_table);

        new ItemAPIHelper(this).execute();
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
        Object tag = ((ViewGroup)view.getParent()).getTag();

        if(tag != null) {
            String itemId = tag.toString();
            new ItemAPIHelper(this).execute("delete", itemId);

            refresh_items();
        } else {
            Toast.makeText(this, "Failed to delete item.", Toast.LENGTH_SHORT).show();
            Log.e("ViewActivity", "Failure to get id for deleting item");
        }
    }

    public void check_item(View view) {
        Object tag = ((ViewGroup)view.getParent()).getTag();

        if( tag != null ) {
            String id = tag.toString();
            new ItemAPIHelper(this).execute("check", id);

            refresh_items();
        } else {
            Log.e("ViewActivity", "Failure to get id for item.");
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
        String listId = getIntent().getStringExtra("listId");
        AddingDialog list_dialog = AddingDialog.newInstance(listId, "New Item", "item");
        list_dialog.show(ft, "dialog");
    }

    public void refresh_items(View view) {
        refresh_items();
    }

    public static void refresh_items() {
        item_table.removeAllViews();

        new ItemAPIHelper(self).execute();
    }
}

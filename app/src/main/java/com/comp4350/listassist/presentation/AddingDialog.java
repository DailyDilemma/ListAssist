package com.comp4350.listassist.presentation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.comp4350.listassist.R;
import com.comp4350.listassist.business.ListAPIHelper;
import com.comp4350.listassist.objects.ShoppingList;

/**
 * Created by Daniel on 3/16/2016 for ListAssist.
 */
public class AddingDialog extends DialogFragment {
    static AddingDialog newInstance(String name, String api_call_type) {
        AddingDialog i = new AddingDialog();

        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("api_call_type", api_call_type);
        i.setArguments(args);

        return i;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
                // Set title
                .setTitle(getArguments().getString("name"))
                // Add action buttons
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Add through appropriate api call
                        String call_type = getArguments().getString("api_call_type");

                        if(call_type.equals("list")) {
                            //TODO: Add list with api call, refresh lists
                            EditText name_field = (EditText)((AlertDialog)dialog).findViewById(R.id.new_name);
                            String inp_name = name_field.getText().toString();

                            if(inp_name != null && !inp_name.equals("")) {
                                new ListAPIHelper().execute("make", inp_name);
                                MainActivity.refresh_table();
                            } else {
                                Toast.makeText(((AlertDialog) dialog).getContext(), "Can't add list, name not valid.", Toast.LENGTH_SHORT).show();
                            }
                        } else if(call_type.equals("item")) {
                            //TODO: Add item with api call, refresh list
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddingDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}

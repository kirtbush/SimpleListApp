package com.kutmastak.simplelistapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Map;


public class ListContentsActivity extends ActionBarActivity {
    private ListItemAdapter listContentsViewAdapter;
    private ListView listContentsView;
    public ImageView itemDefaultImage;
    public String myListName = "";
    final Context context = this;
    String prefName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contents);

        Intent myIntent = getIntent();
        myListName = myIntent.getStringExtra(MainActivity.CURRENT_LIST_NAME);
        setTitle(myListName);

        listContentsView = (ListView)findViewById(R.id.list_contents_view);

        itemDefaultImage = new ImageView(this);
        itemDefaultImage.setImageResource(R.drawable.ic_launcher);

        listContentsViewAdapter = new ListItemAdapter(this);
        listContentsView.setAdapter(listContentsViewAdapter);

        //read the list items from the preferences file
        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences(
                myListName, Context.MODE_PRIVATE);

        prefName = context.getString(R.string.item_list_item_string);
        String myItemString =  sharedPrefs.getString(prefName, "");
        Log.w("ListContents Constructing", "myItemString = "+myItemString);

        //now parse the itemString from preferences, separator is semicolon
        String itemNames[] = myItemString.split(";");

        for (int idx = 0; idx < itemNames.length; idx ++)
        {
            if (itemNames[idx].isEmpty()) //this shouldn't happen, but just in case
                continue;
            listContentsViewAdapter.addItem(itemNames[idx], itemDefaultImage);
        }

        //need to setOnItemLongClickListener
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_contents_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Log.w("ListContentsActionBar", "SettingsPressed");
            return true;
        }
        else if (id == R.id.new_item_action) {
            Log.w("new_item_action", "NewItemPressed");
            final EditText input = new EditText(this);
            AlertDialog.Builder alert = new AlertDialog.Builder(this)
                    .setTitle("New Item")
                    .setMessage("Choose a name for your item")
                    .setView(input)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Editable value = input.getText();
                            Log.w("new_item_action", "Adding a new item named: " +
                                    input.getText().toString());
                            listContentsViewAdapter.addItem(input.getText().toString(), itemDefaultImage);
                            listContentsViewAdapter.notifyDataSetChanged();

                            //Now save the new list to shared preferences

                            SharedPreferences sharedPrefs = context.getSharedPreferences(
                                    myListName, Context.MODE_PRIVATE);

                            Log.w("new_item_action", "R.string.item_list_item_string = "+prefName);
                            SharedPreferences.Editor editor = sharedPrefs.edit();

                            //here we are using a semicolon separator, storing all list items into
                            // the "items" shared pref key
                            editor.putString(prefName, sharedPrefs.getString(prefName, "") +
                                    input.getText().toString() + ";");
                            editor.commit();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Do nothing.
                        }
                    });

            alert.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

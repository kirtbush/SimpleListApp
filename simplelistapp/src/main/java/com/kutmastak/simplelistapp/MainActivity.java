package com.kutmastak.simplelistapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.zip.Inflater;

public class MainActivity extends ActionBarActivity  {
    final Context context = this;
    private ListItemAdapter listViewAdapter;
    private ListView mainListView;
    public ImageView listDefaultImage;
    public final static String CURRENT_LIST_NAME = "Nonce";

    private AdapterView.OnItemLongClickListener onItemLongClickListener = new
            AdapterView.OnItemLongClickListener() {
        public boolean onItemLongClick(AdapterView<?> parent, View arg1,
        int pos, long id) {
            Log.d("item LONG clicked", "pos: " + pos);
            ListView dialogListView;
            ListItemAdapter dialogAdapter = new ListItemAdapter(context);
            dialogListView = new ListView(context);
            dialogListView.setAdapter(dialogAdapter);
            dialogAdapter.addItem("Meh", listDefaultImage);

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);

            alertBuilder.setView(dialogListView);
            alertBuilder.setTitle("List options:");

            AlertDialog alertDialog = alertBuilder.create();

            alertDialog.show();
            return true;
        }
    };

    private AdapterView.OnItemClickListener onItemClickListener = new
        AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Log.d("item clicked", "pos: " + pos);
                Intent intent = new Intent(context, ListContentsActivity.class);
                intent.putExtra(CURRENT_LIST_NAME, listViewAdapter.getItem(pos).getListName());
                startActivity(intent);
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainListView = (ListView)findViewById(R.id.main_list_view);

        listDefaultImage = new ImageView(this);
        listDefaultImage.setImageResource(R.drawable.ic_launcher);

        listViewAdapter = new ListItemAdapter(this);
        mainListView.setAdapter(listViewAdapter);

        //test data
        listViewAdapter.addItem("Test1", listDefaultImage);
        listViewAdapter.addItem("Hello!", listDefaultImage);
        listViewAdapter.addItem("Groceries", listDefaultImage);

        mainListView.setOnItemClickListener(onItemClickListener);
        mainListView.setOnItemLongClickListener(onItemLongClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_action_bar, menu);

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setView(findViewById(R.id.list_popup_menu_view));

        alert.show();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.new_list_action) {
            Log.d("onOptionsItemSelected: new_list_action", "start!");
            final EditText input = new EditText(this);
            AlertDialog.Builder alert = new AlertDialog.Builder(this)
                    .setTitle("New List")
                    .setMessage("Choose a name for your list")
                    .setView(input)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Editable value = input.getText();
                            Log.d("onOptionsItemSelected: new_list_action", "Adding a new item named: "+input.getText().toString());
                            listViewAdapter.addItem(input.getText().toString(), listDefaultImage);
                            listViewAdapter.notifyDataSetChanged();
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

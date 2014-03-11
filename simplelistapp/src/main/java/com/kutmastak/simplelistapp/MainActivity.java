package com.kutmastak.simplelistapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity  {
    public ArrayList<String> myStringArray = new ArrayList<String>();
    //private ArrayAdapter<String> listViewAdapter;
    private ListItemAdapter listViewAdapter;
    private ScheduledExecutorService scheduleTaskExecutor;
    private SecureRandom random = new SecureRandom();
    private ListView mainListView;
    public ImageView listDefaultImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainListView = (ListView)findViewById(R.id.main_item_listview);
        //mainListView = new ListView(this);

        listDefaultImage = new ImageView(this);
        listDefaultImage.setImageResource(R.drawable.ic_launcher);

        listViewAdapter = new ListItemAdapter(this);
        mainListView.setAdapter(listViewAdapter);



// Test Items
//        scheduleTaskExecutor = Executors.newScheduledThreadPool(1);
//        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    public void run() {
//
//                        String newString = new BigInteger(130, random).toString(32);
//                        //myStringArray.add(newString);
//                        listViewAdapter.addItem(newString, listDefaultImage);
//                        Log.d("TaskExecuter", "Added: " + newString);
//                        //Log.d("TaskExecuter", "ListView Count: " + ((ListView) findViewById(R.id.main_item_listview)).getCount());
//                        listViewAdapter.notifyDataSetChanged();
//                    }
//                });
//            }
//        }, 0, 5, TimeUnit.SECONDS);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


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
            Log.d("onOptionsItemSelected: new_list_action", "Adding a new item to the list view");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

package com.kutmastak.simplelistapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;


public class ListContentsActivity extends ActionBarActivity {
    private ListItemAdapter listContentsViewAdapter;
    private ListView listContentsView;
    public ImageView itemDefaultImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contents);

        String myListName = "";

        Intent myIntent = getIntent();
        myListName = myIntent.getStringExtra(MainActivity.CURRENT_LIST_NAME);
        setTitle(myListName);

        listContentsView = (ListView)findViewById(R.id.list_contents_view);

        itemDefaultImage = new ImageView(this);
        itemDefaultImage.setImageResource(R.drawable.ic_launcher);

        listContentsViewAdapter = new ListItemAdapter(this);
        listContentsView.setAdapter(listContentsViewAdapter);

        //test data
        listContentsViewAdapter.addItem("TestItem1", itemDefaultImage);
        listContentsViewAdapter.addItem("TestItem2", itemDefaultImage);

        //need to setOnItemLongClickListener
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_contents, menu);
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
        return super.onOptionsItemSelected(item);
    }

}

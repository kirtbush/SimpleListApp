package com.kutmastak.simplelistapp;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kirt on 3/9/14.
 */
public class ListItemAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<SimpleListItem> mItems = new ArrayList<SimpleListItem>();

    public ListItemAdapter(Context context) {
        this.context = context;
    }

    public void addItem(String name, ImageView image) {

        mItems.add(new SimpleListItem(name, image));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public SimpleListItem getItem(int index) {
        return mItems.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.text_view, parent, false);

        ImageView itemImageView = (ImageView) rootView.findViewById(R.id.list_item_imageview);

        TextView itemTextView = (TextView) rootView.findViewById(R.id.list_item_textview);

        SimpleListItem item = getItem(index);
        itemTextView.setText(item.getListName());

        //Resources resources = getResources();
        //itemImageView.setImageResource(item.getImage().getId());
        itemImageView.setImageResource(R.drawable.ic_launcher);
        return rootView;
    }
}

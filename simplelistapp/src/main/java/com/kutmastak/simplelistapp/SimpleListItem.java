package com.kutmastak.simplelistapp;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by Kirt on 3/9/14.
 */
public class SimpleListItem {
    private String mListName = "New List";
    private ImageView mImage;

    public SimpleListItem(String listName, ImageView image)
    {
        setListName(listName);
        setImage(image);
    }

    public String getListName() {
        return mListName;
    }

    public void setListName(String mListName) {
        this.mListName = mListName;
    }

    public ImageView getImage() {
        return mImage;
    }

    public void setImage(ImageView mImage) {
        this.mImage = mImage;
    }
}

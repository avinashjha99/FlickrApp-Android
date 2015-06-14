package com.avin.flickrapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.avin.flickerapp.model.Item;
import com.avin.flickrapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

/**
 * Created by avin on 14/06/15.
 */
public class FlickrAdapter extends ArrayAdapter<Item>{

    private class Feed{
        ImageView imgFeed;
    }

    public FlickrAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            convertView= ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_feed, null);
            Feed feed= new Feed();
            feed.imgFeed= (ImageView) convertView.findViewById(R.id.img_feed);
            convertView.setTag(feed);
        }
        Item item= getItem(position);
        Feed feed= (Feed) convertView.getTag();
        Picasso.with(getContext())
                .load(item.getMedia().getM())
                .placeholder(R.drawable.downloads_icon)
                .error(R.drawable.download_failed_icon)
                .into(feed.imgFeed);
        return convertView;
    }

}

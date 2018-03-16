package com.edge.weather.unithon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by sj on 2018-01-26.
 */

public class CollectionViewAdapter extends BaseAdapter {
    private ArrayList<CollectionDTO> myitems= new ArrayList<>();

    @Override
    public int getCount() {
        return myitems.size();
    }

    @Override
    public Object getItem(int position) {
        return myitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.collection_item,parent,false);
        }
        ImageView item_imageView = (ImageView) convertView.findViewById(R.id.item_imageView);



        CollectionDTO items = (CollectionDTO) getItem(position);
        item_imageView.setImageDrawable(items.getIcon());

        return convertView;
    }

    public void addItem(Drawable Icon){
        CollectionDTO item = new CollectionDTO();
        item.setIcon(Icon);
        myitems.add(item);
    }
}
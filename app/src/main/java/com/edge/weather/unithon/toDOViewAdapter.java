package com.edge.weather.unithon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by student on 2018-01-23.
 */

public class toDOViewAdapter extends BaseAdapter {
    public static ArrayList<JsonObject> myitems= new ArrayList<>();
    public static int i=0;
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
            convertView = layoutInflater.inflate(R.layout.write_item,parent,false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView until = (TextView) convertView.findViewById(R.id.until);
        final CheckBox checkBox=(CheckBox)convertView.findViewById(R.id.checkbox);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked()){
                    checkBox.setChecked(true);
                    i++;
                    if(i==1){
                        MainActivity.level_1_image.setVisibility(View.GONE);
                        MainActivity.level_3_image.setVisibility(View.GONE);
                        MainActivity.userimagehappy.setVisibility(View.GONE);
                        MainActivity.userimageunhappy.setVisibility(View.GONE);
                        MainActivity.level_2_image.setVisibility(View.VISIBLE);
                    }else{
                        MainActivity.level_1_image.setVisibility(View.GONE);
                        MainActivity.level_2_image.setVisibility(View.GONE);
                        MainActivity.userimagehappy.setVisibility(View.GONE);
                        MainActivity.userimageunhappy.setVisibility(View.GONE);
                        MainActivity.level_3_image.setVisibility(View.VISIBLE);
                    }
                }else{
                    checkBox.setChecked(false);
                    i--;
                }
            }
        });

        title.setText(((JsonObject)getItem(position)).get("title").toString());
        until.setText((((JsonObject)getItem(position)).get("date").toString()));
        return convertView;
    }
    public void addItem(JsonObject title){
        if(myitems.size()<5) {
            myitems.add(title);
            this.notifyDataSetChanged();
        }else{
            //5개가 꽉찼는데 더 추가할려고 하는 경우
        }

    }

}

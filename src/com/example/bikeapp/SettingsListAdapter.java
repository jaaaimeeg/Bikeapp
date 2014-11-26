package com.example.bikeapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsListAdapter extends BaseAdapter {
    
    private Context context;
    private ArrayList<SettingsItem> settingsList;
    private int padding;
    private int textSize;
     
    public SettingsListAdapter(Context context, ArrayList<SettingsItem> settingsList){
        this.context = context;
        this.settingsList = settingsList;
    }
 
    @Override
    public int getCount() {
        return settingsList.size();
    }
 
    @Override
    public Object getItem(int position) {       
        return settingsList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.settings_item, null);
        }
        
        TextView txtTitle = (TextView) convertView.findViewById(R.id.titulo);
        TextView txtLegend = (TextView) convertView.findViewById(R.id.leyenda);
              
        txtTitle.setText(settingsList.get(position).getTitle());
        txtLegend.setText(settingsList.get(position).getLegend());

         
        return convertView;
    }
 
}
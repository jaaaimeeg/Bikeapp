package com.example.bikeapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import components.*;

import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;


@SuppressLint("NewApi") 
public class TipsFragment extends Fragment  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tips_tab, null);
        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.list);
        
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        elv.setIndicatorBounds(width-GetDipsFromPixel(35), width-GetDipsFromPixel(5));
        
        elv.setAdapter(new TipsListAdapter(TipsFragment.this.getActivity()));
        return v;
    }
    
    public int GetDipsFromPixel(float pixels)
    {
            // Get the screen's density scale
            final float scale = getResources().getDisplayMetrics().density;
            // Convert the dps to pixels, based on density scale
            return (int) (pixels * scale + 0.5f);
    } 
}
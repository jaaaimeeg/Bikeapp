package com.example.bikeapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import components.ImageView;

public class AcercaFragment extends Fragment  {
	
	ImageView img1;
	Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.acerca, container, false);
        context = inflater.getContext();
        getActivity().getActionBar().setTitle(Html.fromHtml("<big> Acerca de </big>"));
        getActivity().getActionBar().setIcon(null);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().getActionBar().setHomeButtonEnabled(true);
        LinearLayout lay = (LinearLayout) v.findViewById(R.id.layoutimg);
        new ImageView(context,lay,459,94,R.raw.logo);
        
        return v;
    }
    
    public void onDestroy(){
        super.onDestroy();
    }
}
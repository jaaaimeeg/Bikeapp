package com.example.bikeapp;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ConoceTuBicicleta extends Fragment  {
	
	ImageView img1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bicicleta, container, false);

        getActivity().getActionBar().setTitle(Html.fromHtml("<big> Conoce tu Bicicleta </big>"));
        getActivity().getActionBar().setIcon(null);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().getActionBar().setHomeButtonEnabled(true);
        
        img1 = (ImageView) v.findViewById(R.id.bicicleta_partes);
        
        return v;
    }
    
    public void onDestroy(){
        super.onDestroy();

        img1.setImageDrawable(null);
    }
}
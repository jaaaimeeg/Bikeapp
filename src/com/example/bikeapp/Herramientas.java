package com.example.bikeapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Herramientas extends Fragment  {
	
	private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_herramientas, container, false);
    	context = inflater.getContext();

        getActivity().getActionBar().setTitle(Html.fromHtml("<big> Kit de Herramientas </big>"));
        getActivity().getActionBar().setIcon(null);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().getActionBar().setHomeButtonEnabled(true);
        
        return v;
    }
}
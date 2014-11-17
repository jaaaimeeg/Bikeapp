package com.example.bikeapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import components.*;

@SuppressLint("NewApi") 
public class TipsFragment extends Fragment  {

	private Context context;
	
	public TipsFragment(){}
	
	@SuppressWarnings("deprecation")
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	context = inflater.getContext();
    	
		int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
    	int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
    	RelativeLayout mainLayout = new RelativeLayout(context);
		LinearLayout layout2 = new LinearLayout(context);
		LinearLayout layout3 = new LinearLayout(context);

		layout2.setOrientation(LinearLayout.HORIZONTAL);
		layout3.setOrientation(LinearLayout.VERTICAL);
		layout3.setGravity(Gravity.CENTER_VERTICAL);
		
		new ImageView(context,layout2,(int)(width*0.5),(int)(height*0.3),R.raw.logo);
		TextView nombre = new TextView(context,"Tips",16);
		TextView correo = new TextView(context,"Bikeapp",10);
		
		layout3.addView(nombre);
		layout3.addView(correo);
		
		layout2.addView(layout3);
		mainLayout.addView(layout2,-1,-1);
    	View rootView = mainLayout;
		
		return rootView;
	}
}

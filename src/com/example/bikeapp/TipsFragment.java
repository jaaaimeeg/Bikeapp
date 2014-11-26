package com.example.bikeapp;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import components.*;

import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi") 
public class TipsFragment extends Fragment  {
	
	private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tips_tab, null);
    	context = inflater.getContext();
        ListView elv = (ListView) v.findViewById(R.id.listView1);
        ArrayList<NavDrawerItem> elements = new ArrayList<NavDrawerItem>();
		elements.add(new NavDrawerItem("Conoce tu bicicleta"));
		elements.add(new NavDrawerItem("Kit de herramientas"));
		elements.add(new NavDrawerItem("Arreglos y Mantención"));
		elements.add(new NavDrawerItem("Seguridad Vial"));
		NavDrawerListAdapter adapter = new NavDrawerListAdapter(context, elements, 50);
		elv.setAdapter(adapter);
		elv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				displayView(arg2);
				
			}
		});
        return v;
    }
    
    private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		// load Perfil
		case 0:
			fragment = new ConoceTuBicicleta();
			break;
		// load Alertas
		case 1:
			fragment = new Herramientas();
			break;
		// load Tips
		case 2:
			fragment = new TipsFragment();
			break;
		case 3:
			fragment = new TipsFragment();
			break;

		default:
			break;
		}
		android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
	}
    
    public int GetDipsFromPixel(float pixels)
    {
            // Get the screen's density scale
            final float scale = getResources().getDisplayMetrics().density;
            // Convert the dps to pixels, based on density scale
            return (int) (pixels * scale + 0.5f);
    } 
}
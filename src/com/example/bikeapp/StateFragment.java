package com.example.bikeapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class StateFragment extends Fragment {

	private Context context;
	private View rootView;
	private ListView listaSt;
	private EditText estado;
	private ArrayList<NavDrawerItem> elements;

	public StateFragment() {
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = inflater.getContext();
		rootView = inflater.inflate(R.layout.fragment_estados, container, false);
		listaSt = (ListView) rootView.findViewById(R.id.listView1);
		listaSt.setHeaderDividersEnabled(true);
		estado = (EditText) rootView.findViewById(R.id.editText1);
		
		elements = new ArrayList<NavDrawerItem>();
		elements.add(new NavDrawerItem("En ruta"));
		elements.add(new NavDrawerItem("En casa"));
		elements.add(new NavDrawerItem("Hacia el trabajo"));
		elements.add(new NavDrawerItem("Hacia la escuela"));
		elements.add(new NavDrawerItem("Hacia la Universidad"));
		NavDrawerListAdapter adapter = new NavDrawerListAdapter(context, elements,0,18);
		listaSt.setAdapter(adapter);
		listaSt.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				estado.setText(elements.get(arg2).getTitle());
				
			}
		});

		return rootView;
	}
}


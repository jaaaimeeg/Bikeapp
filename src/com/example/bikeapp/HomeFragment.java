package com.example.bikeapp;

import java.util.ArrayList;
import java.util.List;

import components.ImageView;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.TextView;

public class HomeFragment extends Fragment {

	private Context context;
	private View rootView;

	public HomeFragment() {
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
    	context = inflater.getContext();
    	rootView = inflater.inflate(R.layout.fragment_perfil, container, false);
		ListView listaB = (ListView) rootView.findViewById(R.id.list_buttons);
		TextView txtnombre = (TextView) rootView.findViewById(R.id.textView1);
		TextView txtmail = (TextView) rootView.findViewById(R.id.textView2);
		txtnombre.setText("Nombre Usuario");
		txtmail.setText("Correo Usuario");
		List<Item> elements = new ArrayList<Item>();
		elements.add(new Item("Estado"));
		elements.add(new Item("Actividad"));
		elements.add(new Item("Editar Perfil"));
		ItemIcon adapter = new ItemIcon(context, elements);
		listaB.setAdapter(adapter);
		listaB.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.print("llego");
				displayView(arg2);
				
			}
		});

		return rootView;
	}

	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		// load Perfil
		case 0:
			fragment = new StateFragment();
			break;
		// load Alertas
		case 1:
			fragment = new AlertFragment();
			break;
		// load Tips
		case 2:
			fragment = new TipsFragment();
			break;

		default:
			break;
		}
		android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
	}
}

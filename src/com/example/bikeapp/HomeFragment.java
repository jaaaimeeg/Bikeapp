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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import android.widget.TextView;

public class HomeFragment extends Fragment {

	private Context context;
	private View rootView;

	public HomeFragment() {
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
<<<<<<< HEAD
			Bundle savedInstanceState) {
		context = inflater.getContext();
		rootView = inflater.inflate(R.layout.fragment_perfil, container, false);
		ListView listaB = (ListView) rootView.findViewById(R.id.list_buttons);
		listaB.setHeaderDividersEnabled(true);
		TextView txt1 = (TextView) rootView.findViewById(R.id.textView1);
		TextView txt2 = (TextView) rootView.findViewById(R.id.textView2);
		txt1.setText("Nombre Persona");
		txt2.setText("Correo Persona");
=======
            Bundle savedInstanceState){
    	context = inflater.getContext();
    	rootView = inflater.inflate(R.layout.fragment_perfil, container, false);
		LinearLayout layout2 = (LinearLayout) rootView.findViewById(R.id.layout1);
		LinearLayout layout3 = new LinearLayout(context);
		layout2.findViewById(R.id.layout1);
		layout3.setOrientation(LinearLayout.VERTICAL);
		layout3.setGravity(Gravity.CENTER_VERTICAL);
		new ImageView(context,layout2,(int)(150),(int)(150),R.raw.avatar);
		
		String text = "<font color=#F47F1F>Jaime Galeano</font>";
		String text2 = "<font color=#F47F1F>jgaleano2011@alu.uct.cl</font>";
		
		TextView nombre = new TextView(context,"Jaime Galeano",16,R.drawable.texto);
		TextView correo = new TextView(context,"jgaleano2011@alu.uct.cl",10,R.drawable.texto);
		
		nombre.setText(Html.fromHtml(text));
		correo.setText(Html.fromHtml(text2));
		
		layout3.addView(nombre);
		layout3.addView(correo);
		layout3.setPadding(10, 50, 10, 10);
		layout2.addView(layout3);
		layout2.setGravity(Gravity.CENTER_HORIZONTAL);
		
		ListView listaB = (ListView) rootView.findViewById(R.id.list_buttons);
>>>>>>> origin/master
		
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

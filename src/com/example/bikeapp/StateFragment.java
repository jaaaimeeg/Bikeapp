package com.example.bikeapp;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.UsuarioFacade;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
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
		
		SharedPreferences settings = getActivity().getSharedPreferences("Bikeapp", 0);
		String username = settings.getString("username", null);

		UsuarioFacade uf = new UsuarioFacade(context);
		Usuario user = uf.get(username);

		listaSt = (ListView) rootView.findViewById(R.id.listView1);
		listaSt.setHeaderDividersEnabled(true);
		estado = (EditText) rootView.findViewById(R.id.pwddelete);
		estado.setText(user.getEstado());
		ImageView stic = (ImageView) rootView.findViewById(R.id.estadoicon);
		stic.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				SharedPreferences settings = getActivity().getSharedPreferences("Bikeapp", 0);
				String username = settings.getString("username", null);
				
				UsuarioFacade uf = new UsuarioFacade(context);
				Usuario user = uf.get(username);
				
				user.setEstado(estado.getText().toString());
				int x = uf.editarUser(user);
				estado.setText(user.getEstado());
			}
		});
		
		
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


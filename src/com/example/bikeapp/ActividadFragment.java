package com.example.bikeapp;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ActividadFragment extends Fragment {

	private Context context;
	private View rootView;
	private ListView listaSt;
	private EditText estado;
	private ArrayList<NavDrawerItem> elements;

	public ActividadFragment() {
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = inflater.getContext();
		rootView = inflater.inflate(R.layout.fragment_actividad, container, false);
		TextView distancia1 = (TextView) rootView.findViewById(R.id.distancia1);
		TextView distancia2 = (TextView) rootView.findViewById(R.id.distancia2);
		TextView distancia3 = (TextView) rootView.findViewById(R.id.distancia3);
		TextView tiempo1 = (TextView) rootView.findViewById(R.id.tiempo1);
		TextView tiempo2 = (TextView) rootView.findViewById(R.id.tiempo2);
		TextView tiempo3 = (TextView) rootView.findViewById(R.id.tiempo3);
		distancia1.setText("0 Km");
		distancia2.setText("0 Km");
		distancia3.setText("0 Km");
		tiempo1.setText("--:--:--");
		tiempo2.setText("--:--:--");
		tiempo3.setText("--:--:--");
		return rootView;
	}
}
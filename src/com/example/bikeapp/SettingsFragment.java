package com.example.bikeapp;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SettingsFragment extends Fragment {
	
	Context context;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, null);
    	context = inflater.getContext();
        ListView elv = (ListView) v.findViewById(R.id.listView12);
		ArrayList<SettingsItem> elements = new ArrayList<SettingsItem>();
		elements.add(new SettingsItem("Servicios de Localización de Google",
				"Permitir a la aplicación usar datos desde origenes como el Wi-fi y redes móviles para determinar su ubicación próxima"));
		elements.add(new SettingsItem("Satélites GPS",
				"Permitir a la aplicación usar GPS para localizar su ubicación"));
		SettingsListAdapter adapter = new SettingsListAdapter(context, elements);
		elv.setAdapter(adapter);
        return v;
    }
}

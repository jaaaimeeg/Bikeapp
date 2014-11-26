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
		elements.add(new SettingsItem("Servicios de Localizaci�n de Google",
				"Permitir a la aplicaci�n usar datos desde origenes como el Wi-fi y redes m�viles para determinar su ubicaci�n pr�xima"));
		elements.add(new SettingsItem("Sat�lites GPS",
				"Permitir a la aplicaci�n usar GPS para localizar su ubicaci�n"));
		SettingsListAdapter adapter = new SettingsListAdapter(context, elements);
		elv.setAdapter(adapter);
        return v;
    }
}

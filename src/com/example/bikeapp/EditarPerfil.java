package com.example.bikeapp;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

public class EditarPerfil extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.asd, container, false);

        getActivity().getActionBar().setTitle(Html.fromHtml("<medium> Editar Perfil </medium>"));
        getActivity().getActionBar().setIcon(null);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().getActionBar().setHomeButtonEnabled(true);
        
        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.expandableListView1);
        
        TipsListAdapter tla = new TipsListAdapter(getActivity());
        
        elv.setAdapter(tla);
        
        return v;
    }
    
    public void onDestroy(){
        super.onDestroy();
    }

}

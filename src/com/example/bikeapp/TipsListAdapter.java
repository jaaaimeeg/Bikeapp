package com.example.bikeapp;

import android.app.Activity;
import android.graphics.Color;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import components.Button;
import components.TextEdit;
import components.TextView;

public class TipsListAdapter extends BaseExpandableListAdapter {
		Activity activity;
		
		public TipsListAdapter(Activity a) {
			this.activity = a;
		}
		
        private String[] groups = { "Agregar Ciudad", "Cambiar Contraseña", "Privacidad", "Eliminar Cuenta" };
 
        private String[][] children = {
            { "Ingresar Región", "Ingresar Ciudad", "Guardar"},
            { "Nueva Contraseña", "Confirmar Contraseña", "Guardar"},
            { "Estado", "Foto"},
            { "Contraseña", "Eliminar"}
        };
 
        @Override
        public int getGroupCount() {
            return groups.length;
        }
 
        @Override
        public int getChildrenCount(int i) {
            return children[i].length;
        }
 
        @Override
        public Object getGroup(int i) {
            return groups[i];
        }
 
        @Override
        public Object getChild(int i, int i1) {
            return children[i][i1];
        }
 
        @Override
        public long getGroupId(int i) {
            return i;
        }
 
        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }
 
        @Override
        public boolean hasStableIds() {
            return true;
        }
 
        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(this.activity);
            textView.setText(getGroup(i).toString());
            textView.setPadding(70,70,70,70);
            
            textView.setTextColor(Color.rgb(158,158,159));
            textView.setTextSize(18);
            
            textView.setBackgroundColor(Color.WHITE);
            return textView;
        }
 
        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        	RelativeLayout rl = new RelativeLayout(this.activity);
    		rl.setGravity(Gravity.CENTER);
        	if(i==0){
				Spinner sp = new Spinner(this.activity);
				ArrayAdapter adapter = null;
				if(i1==0){
					adapter = ArrayAdapter.createFromResource(this.activity, R.array.regiones, android.R.layout.simple_spinner_item);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					sp.setAdapter(adapter);
	        		rl.addView(sp);
				} if(i1==1) {
					adapter = ArrayAdapter.createFromResource(this.activity, R.array.ciudades, android.R.layout.simple_spinner_item);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					sp.setAdapter(adapter);
	        		rl.addView(sp);
				} if(i1==2){
					Button x = new Button(this.activity);
					x.setText(getChild(i, i1).toString());
					x.setBackgroundDrawable(this.activity.getResources().getDrawable(R.drawable.gradient_orange));
					rl.addView(x);
				}
        	} if(i==1){
        		if(i1==2){
        			Button x = new Button(this.activity);
					x.setText(getChild(i, i1).toString());
					x.setBackgroundDrawable(this.activity.getResources().getDrawable(R.drawable.gradient_orange));
					rl.addView(x);
        		} else { 
        			TextEdit te = new TextEdit(this.activity);
            		te.setHint(getChild(i, i1).toString());
            		te.setTextSize(16);
            		te.setPadding(50, 50, 50, 50);
            		te.setTransformationMethod(PasswordTransformationMethod.getInstance());
            		rl.setGravity(Gravity.CENTER);
            		rl.addView(te);
        		}
        	} if(i==2){
        		Spinner sp = new Spinner(this.activity);
				ArrayAdapter adapter = null;
        		if(i1==0){
        			LinearLayout ly = new LinearLayout(this.activity);
        			ly.setOrientation(LinearLayout.HORIZONTAL);
        			ly.setGravity(Gravity.CENTER);
        			TextView tv = new TextView(this.activity,"Estado");
					adapter = ArrayAdapter.createFromResource(this.activity, R.array.Estado, android.R.layout.simple_spinner_item);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					sp.setAdapter(adapter);
					ly.addView(tv);
					ly.addView(sp);
	        		rl.addView(ly);
				} if(i1==1) {
					LinearLayout ly = new LinearLayout(this.activity);
        			ly.setOrientation(LinearLayout.HORIZONTAL);
        			ly.setGravity(Gravity.CENTER);
        			TextView tv = new TextView(this.activity,"Foto");
					adapter = ArrayAdapter.createFromResource(this.activity, R.array.Estado, android.R.layout.simple_spinner_item);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					sp.setAdapter(adapter);
					ly.addView(tv);
					ly.addView(sp);
	        		rl.addView(ly);
				}
        	}

            
            return rl;
        }
 
        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
 
    }
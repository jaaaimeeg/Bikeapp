package com.example.bikeapp;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import components.TextView;

public class TipsListAdapter extends BaseExpandableListAdapter {
		Activity activity;
		
		
		public TipsListAdapter(Activity a) {
			this.activity = a;
		}
		
        private String[] groups = { "Arreglos y Mantencion", "Conoce tu Bicicleta", "Kit de Herramientas", "Seguridad Vial" };
 
        private String[][] children = {
            { "Ajuste de Frenos", "Chequeo Semanal", "Chequeo Mensual", "Limpieza y Lubricacion", "Pinchazos" },
            { "Frenos", "Marco", "Llantas", "Horquilla" },
            { "Llaves necesarias", "Elementos necesarios" },
            { "Como transitar de noche", "Lugares seguros para descanzar" }
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
            textView.setTextSize(40);
            
            textView.setBackgroundColor(Color.WHITE);
            return textView;
        }
 
        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(this.activity);
            textView.setText(getChild(i, i1).toString());
            
            // sets child colors
            textView.setTextColor(Color.rgb(158,158,159));
            textView.setPadding(50, 50, 50, 50);
            textView.setTextSize(35);
            
            textView.setBackgroundColor(Color.rgb(235,239,241));
            
            return textView;
        }
 
        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
 
    }
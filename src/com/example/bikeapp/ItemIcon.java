package com.example.bikeapp;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("InlinedApi") 
public class ItemIcon extends ArrayAdapter<Item> {

	Context context;
	List<Item> items;
	
	/**
	 * Constructor clase ItemIcon utilizado para la barra lateral de 
	 * menu
	 */
	public ItemIcon(Context context, List<Item> items) {
		super(context, android.R.layout.simple_list_item_1, items);
		this.context=context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout layout=new LinearLayout(this.context);
		layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.list_selector));
		
		ImageView image=new ImageView(this.context);
		
		image.setImageResource(this.items.get(position).getIcon());
		image.setPadding(10, 10, 10, 10);
		
		TextView text=new TextView(this.context);
		text.setText(this.items.get(position).getText());
		
		text.setHeight(100);
		text.setPadding(20,30,20,20);
		text.setTextSize(19);
		
		layout.addView(image,110,110);
		layout.addView(text);
		
		return layout;
	}

}

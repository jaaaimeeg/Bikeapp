package com.example.bikeapp;

public class Item {
	private String text;
	private int icon;
	
	public Item() {};
	public Item(String t, int i) {
		this.text = t;
		this.icon = i;
	}
	
	public int getIcon() { return this.icon; }
	public String getText() { return this.text; }
}

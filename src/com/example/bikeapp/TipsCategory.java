package com.example.bikeapp;

import java.util.ArrayList;
import java.util.List;

public class TipsCategory {
	private long id;
	private String name;
    private String descr;
    private List<TipsItems> itemList = new ArrayList<TipsItems>();

    public TipsCategory() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public List<TipsItems> getItemList() {
		return itemList;
	}

	public void setItemList(List<TipsItems> itemList) {
		this.itemList = itemList;
	}

	public TipsCategory(long id, String name, String descr,
			List<TipsItems> itemList) {
		super();
		this.id = id;
		this.name = name;
		this.descr = descr;
		this.itemList = itemList;
	}
    
}

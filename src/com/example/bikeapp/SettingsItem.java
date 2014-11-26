package com.example.bikeapp;

public class SettingsItem {
    
    private String title;
    private String legend;
     
    public SettingsItem(){}
 
    public SettingsItem(String title, String legend){
        this.title = title;
        this.legend = legend;
    }
     
    public String getTitle(){
        return this.title;
    }
     
    public String getLegend(){
        return this.legend;
    }
     
    public void setTitle(String title){
        this.title = title;
    }
     
    public void setLegend(String legend){
        this.legend = legend;
    }
     
}
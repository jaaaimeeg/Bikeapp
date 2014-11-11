package com.example.bikeapp;

import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;
import com.example.bikeapp.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
 

@SuppressLint("NewApi") public class AlertFragment extends Fragment {

protected MapView map;
private MyLocationOverlay myLocationOverlay;
private Context context;
private View rootView;

     
    public AlertFragment(){}
     
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
    	rootView = inflater.inflate(R.layout.fragment_home, container, false);
    	context = inflater.getContext();
    	setupMapView();
		setupMyLocation();
		return rootView;

    }
    
    // set your map and enable default zoom controls 
    private void setupMapView() {
      this.map = (MapView) rootView.findViewById(R.id.map);
      //map.setBuiltInZoomControls(true);
    }

    // set up a MyLocationOverlay and execute the runnable once we have a location fix 
    private void setupMyLocation() {
      this.myLocationOverlay = new MyLocationOverlay(context, map);
      myLocationOverlay.enableMyLocation();
      myLocationOverlay.runOnFirstFix(new Runnable() {
        @Override
        public void run() {
          GeoPoint currentLocation = myLocationOverlay.getMyLocation();
          map.getController().animateTo(currentLocation);
          map.getController().setZoom(20);
          map.getOverlays().add(myLocationOverlay);
          myLocationOverlay.setFollowing(true);
        }
      });
    }

    // enable features of the overlay 
	public void onResume() {
      myLocationOverlay.enableMyLocation();
      myLocationOverlay.enableCompass();
      super.onResume();
    }
	
	

    // disable features of the overlay when in the background 
    public void onPause() {
      super.onPause();
      myLocationOverlay.disableCompass();
      myLocationOverlay.disableMyLocation();
    }

  
    public boolean isRouteDisplayed() {
      return false;
    }

}
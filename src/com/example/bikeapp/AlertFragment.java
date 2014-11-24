package com.example.bikeapp;

import com.mapquest.android.maps.AnnotationView;
import com.mapquest.android.maps.CircleOverlay;
import com.mapquest.android.maps.DefaultItemizedOverlay;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.ItemizedOverlay;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;
import com.mapquest.android.maps.OverlayItem;
import com.example.bikeapp.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
 

@SuppressLint("NewApi") public class AlertFragment extends Fragment {

protected MapView map;
private MyLocationOverlay myLocationOverlay;
private Context context;
private View rootView;
protected Button createPointButton;
AnnotationView annotation;

     
    public AlertFragment(){}
     
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	
    	
    	rootView = inflater.inflate(R.layout.fragment_home, container, false);
    	context = inflater.getContext();
    	
    	
    	createPointButton=(Button)rootView.findViewById(R.id.createPointButton);
        createPointButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tester();
			}
		});
      
        
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
    
    private void addOverlay(String alerta) {
        Drawable icon = getResources().getDrawable(R.drawable.caution_512);
    	final DefaultItemizedOverlay overlay = new DefaultItemizedOverlay(icon);
    	
    	// add items with a title and a snippet
    	OverlayItem item1 = new OverlayItem(myLocationOverlay.getMyLocation(), "Alerta", alerta);
    	overlay.addItem(item1);
    	
    	// add a tap listener
//    	overlay.setTapListener(new ItemizedOverlay.OverlayTapListener() {
//			@Override
//			public void onTap(GeoPoint pt, MapView MapView) {
//				// when tapped, show the annotation for the overlayItem
//				int lastTouchedIndex = overlay.getLastFocusedIndex();
//				if(lastTouchedIndex>-1){
//					OverlayItem tapped = overlay.getItem(lastTouchedIndex);
//					annotation.showAnnotationView(tapped);
//				}
//			}
//		});
    	
    	overlay.setTapListener(new ItemizedOverlay.OverlayTapListener() {
			@Override
			public void onTap(GeoPoint pt, MapView MapView) {
				// when tapped, show the annotation for the overlayItem
				int lastTouchedIndex = overlay.getLastFocusedIndex();
				if(lastTouchedIndex>-1){
					OverlayItem tapped = overlay.getItem(lastTouchedIndex);
					Toast.makeText(getActivity().getApplicationContext(), tapped.getTitle() + ": "+
					tapped.getSnippet(), Toast.LENGTH_SHORT).show();
				}
			}
		});
    	
    	
    	
    	map.getOverlays().add(overlay);
    	map.invalidate();
    }
    
    public void tester(){
    	AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

    	alert.setTitle("Agregar Alerta");
    	alert.setMessage("Mensaje");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(getActivity());
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    	  String value = input.getText().toString();
    	  addOverlay(value);
    	  }
    	});

    	alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
    	  public void onClick(DialogInterface dialog, int whichButton) {
    	    // Canceled.
    	  }
    	});

    	alert.show();
    }

}
package com.example.bikeapp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

@SuppressLint("NewApi")
public class AlertFragment extends Fragment {

	protected MapView map;
	private MyLocationOverlay myLocationOverlay;
	private Context context;
	private View rootView;
	protected Button createPointButton;
	AnnotationView annotation;

	public static List<Location> locations = new ArrayList<Location>();

	public AlertFragment() {
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_home, container, false);
		context = inflater.getContext();

		createPointButton = (Button) rootView
				.findViewById(R.id.createPointButton);
		createPointButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tester();
			}
		});

		setupMapView();

		setupMyLocation();
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) getActivity()
				.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location
				// provider.
				locations.add(location);
				Log.i("LOCATION", location.toString());
				Log.i("COUNT POINTS", String.valueOf(locations.size()));
				double distance = 0.0;

//				for (int i = 0; i < locations.size(); i++) {
//					if (i != 0) {
//						distance += calculateDistance(locations.get(i - 1),
//								locations.get(i));
//					}
//				}
//
//				Log.i("DISTANCE", String.valueOf(distance));
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};

		// Register the listener with the Location Manager to receive location
		// updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);

		return rootView;
	}

	// set your map and enable default zoom controls
	private void setupMapView() {
		this.map = (MapView) rootView.findViewById(R.id.map);
		// map.setBuiltInZoomControls(true);
	}

	// set up a MyLocationOverlay and execute the runnable once we have a
	// location fix
	private void setupMyLocation() {
		this.myLocationOverlay = new MyLocationOverlay(context, map) {

			@Override
			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				Log.i(arg0.toString(), arg0.toString());
				super.onLocationChanged(arg0);
			}

		};
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.runOnFirstFix(new Runnable() {
			@Override
			public void run() {
				GeoPoint currentLocation = myLocationOverlay.getMyLocation();

				map.getController().animateTo(currentLocation);
				map.getController().setZoom(30);
				map.getOverlays().add(myLocationOverlay);

				myLocationOverlay.setFollowing(true);

				
			}
		});
	}

	/**
	 * Calculate Distance using two GeoPoints
	 * 
	 * @param StartP
	 * @param EndP
	 * @return double distance of StartP - EndP
	 */
	public double calculateDistance(GeoPoint StartP, GeoPoint EndP) {
		int Radius = 6371;// radius of earth in Km
		double lat1 = StartP.getLatitudeE6() / 1E6;
		double lat2 = EndP.getLatitudeE6() / 1E6;
		double lon1 = StartP.getLongitudeE6() / 1E6;
		double lon2 = EndP.getLongitudeE6() / 1E6;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
				* Math.sin(dLon / 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		double valueResult = Radius * c;
		double km = valueResult / 1;
		DecimalFormat newFormat = new DecimalFormat("####");

		Integer kmInDec = Integer.valueOf(newFormat.format(km));
		double meter = valueResult % 1000;
		Integer meterInDec = Integer.valueOf(newFormat.format(meter));

		Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
				+ " Meter   " + meterInDec);

		return Radius * c;
	}

	// enable features of the overlay
	public void onResume() {
		if (myLocationOverlay != null) {
			myLocationOverlay.enableMyLocation();
			myLocationOverlay.enableCompass();
		}
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
		OverlayItem item1 = new OverlayItem(myLocationOverlay.getMyLocation(),
				"Alerta", alerta);
		overlay.addItem(item1);

		// add a tap listener
		// overlay.setTapListener(new ItemizedOverlay.OverlayTapListener() {
		// @Override
		// public void onTap(GeoPoint pt, MapView MapView) {
		// // when tapped, show the annotation for the overlayItem
		// int lastTouchedIndex = overlay.getLastFocusedIndex();
		// if(lastTouchedIndex>-1){
		// OverlayItem tapped = overlay.getItem(lastTouchedIndex);
		// annotation.showAnnotationView(tapped);
		// }
		// }
		// });

		overlay.setTapListener(new ItemizedOverlay.OverlayTapListener() {
			@Override
			public void onTap(GeoPoint pt, MapView MapView) {
				// when tapped, show the annotation for the overlayItem
				int lastTouchedIndex = overlay.getLastFocusedIndex();
				if (lastTouchedIndex > -1) {
					OverlayItem tapped = overlay.getItem(lastTouchedIndex);
					Toast.makeText(getActivity().getApplicationContext(),
							tapped.getTitle() + ": " + tapped.getSnippet(),
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		map.getOverlays().add(overlay);
		map.invalidate();
	}

	public void tester() {
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

		alert.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();
	}

}
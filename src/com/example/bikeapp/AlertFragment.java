package com.example.bikeapp;
 
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
 
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
import com.mapquest.android.maps.AnnotationView;
import com.mapquest.android.maps.DefaultItemizedOverlay;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.ItemizedOverlay;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;
import com.mapquest.android.maps.OverlayItem;
 
@SuppressLint("NewApi")
public class AlertFragment extends Fragment {
 
        protected MapView map;
        private MyLocationOverlay myLocationOverlay;
        private Context context;
        private View rootView;
        protected Button createPointButton;
        AnnotationView annotation;
 
        public static List<GeoPoint> locations = new ArrayList<GeoPoint>();
        public static double distance = 0.0;
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
               
                this.myLocationOverlay = new MyLocationOverlay(context, map);
                myLocationOverlay.enableMyLocation();
                // Acquire a reference to the system Location Manager
                LocationManager locationManager = (LocationManager) getActivity()
                                .getSystemService(Context.LOCATION_SERVICE);
 
                // Define a listener that responds to location updates
                LocationListener locationListener = new LocationListener() {
                        public void onLocationChanged(Location location) {
                                Log.i("LOCATION", location.toString());
                                Log.i("COUNT POINTS", String.valueOf(locations.size()));
                            
                                setUp2();
                                
                                int lat = (int) (location.getLatitude() * 1E6);
                                int lng = (int) (location.getLongitude() * 1E6);
                                GeoPoint point = new GeoPoint(lat, lng);
                                
                                if (locations.size() > 1 ) {
                                	GeoPoint last = locations.get(locations.size() - 1);
	                                if (point != last) {
	                                	locations.add(point);
	                                	
	                                	for (int i=0; i < locations.size(); i++){
	                                		if (i != 0) 
	                                			distance = calculateDistance(locations.get(i-1),locations.get(i));
	                                	}
	                                }
                                } else {
                                	locations.add(point);
                                }
                                
                                // calculate distance
                                Log.i("DISTANCE", String.valueOf(distance));
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
 
        void setUp2(){
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
 
                //Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
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
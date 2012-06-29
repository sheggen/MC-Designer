package com.uncc.cci.toolkit.newCampaign;

import java.util.ArrayList;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.uncc.cci.toolkit.R;
import com.uncc.cci.toolkit.newCampaign.MyLocation.LocationResult;

import datastructures.Campaign;
/*
 * This screen displays the map at the end of the session
 */
public class ShowMapScreen extends MapActivity {
	MapView mapView;
	ArrayList<Double> latList;
	ArrayList<Double> lngList, altList;
	Button btn;
	MapController mc;
	GeoPoint p;
	MyLocation myLocation = new MyLocation();
	Button submitButton;
	Campaign camp;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_campaign_map_config);
		
		//Bundle b = this.getIntent().getExtras();
		System.out.println("Entering profile matching");
				
		locationClick();
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setSatellite(false);
		mapView.setBuiltInZoomControls(true);
     
		submitButton = (Button)findViewById(R.id.save);
		submitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				int latSpan = mapView.getLatitudeSpan();
				int longSpan = mapView.getLongitudeSpan();
				GeoPoint mapCenter = mapView.getMapCenter();
				System.out.println(mapCenter.toString() + " " + Integer.toString(latSpan) + " " + Integer.toString(longSpan));
				Intent in = new Intent();
				in.putExtra("mapCenter", mapCenter.toString());
				in.putExtra("longSpan", longSpan);
				setResult(1,in);
				finish();
			}
		});
    }
    
	
    private void locationClick() {
        myLocation.getLocation(this, locationResult);
    }

    public LocationResult locationResult = new LocationResult(){
        @Override
        public void gotLocation(final Location location){
        	mc = mapView.getController();
	        try {
				double coordinates[] = {location.getLatitude(), location.getLongitude()};
				double lat = (coordinates[0]);
				double lng = (coordinates[1]);
 
				p = new GeoPoint(
				    (int) (lat * 1E6), 
				    (int) (lng * 1E6));
 
				mc.animateTo(p);
				mc.setZoom(10); 
				mapView.invalidate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }
    };
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
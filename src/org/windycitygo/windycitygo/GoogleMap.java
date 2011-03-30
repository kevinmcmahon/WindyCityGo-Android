package org.windycitygo.windycitygo;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import de.android1.overlaymanager.ManagedOverlay;
import de.android1.overlaymanager.ManagedOverlayItem;
import de.android1.overlaymanager.OverlayManager;
import de.android1.overlaymanager.ZoomEvent;

public class GoogleMap extends MapActivity {
	private static final String CLASSTAG = GoogleMap.class.getSimpleName();
	
		MapView mapView;
		OverlayManager overlayManager;
		
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, GoogleMap.CLASSTAG + " onCreate");
        
        setContentView(R.layout.google_map);
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        overlayManager = new OverlayManager(this, mapView);
        
        GeoPoint location = new GeoPoint(41888233,-87630140);
        
        MapController mc = mapView.getController();
        mc.setCenter(location);
        mc.setZoom(18);    
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.v(Constants.LOGTAG, GoogleMap.CLASSTAG + " onStart");
		
	    Drawable defaultmarker = getResources().getDrawable(R.drawable.marker);     

	    ManagedOverlay managedOverlay = overlayManager.createOverlay("location", defaultmarker);
	    
	    ManagedOverlayItem moi = new ManagedOverlayItem(new GeoPoint(41888233,-87630140),"WindyCityGo","The Westin Chicago River North");
	    
	    managedOverlay.add(moi);
	    
	    //registers the ManagedOverlayer to the MapView
	    overlayManager.populate();
	    
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}

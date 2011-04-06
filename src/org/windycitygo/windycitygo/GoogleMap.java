package org.windycitygo.windycitygo;

import java.util.ArrayList;
import java.util.List;

import org.windycitygo.windycitygo.util.MapHelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

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
	
	private MapView mapView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, GoogleMap.CLASSTAG + " onCreate");
        
        setContentView(R.layout.google_map);
        
        Bundle extras = getIntent().getExtras();
        GeoPoint location = MapHelper.getPoint(Double.parseDouble(extras.getString(Constants.LOCATION_LAT_EXTRA).trim()),
        		Double.parseDouble(extras.getString(Constants.LOCATION_LONG_EXTRA).trim()));
        
	    
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        MapController mc = mapView.getController();
        mc.setCenter(location);
        mc.setZoom(18);
        
        Drawable defaultmarker = getResources().getDrawable(R.drawable.marker);     
	    defaultmarker.setBounds(0,0,defaultmarker.getIntrinsicWidth(),defaultmarker.getIntrinsicHeight());
	    
	    String title = extras.getString(Constants.LOCATION_NAME_EXTRA);
	    String subTitle = extras.getString(Constants.LOCATION_VENUE_LONG_EXTRA);
        mapView.getOverlays().add(new SiteOverlay(new OverlayItem(location, title,subTitle), defaultmarker, getParent()));
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	private class SiteOverlay extends ItemizedOverlay<OverlayItem> {
		
		private List<OverlayItem> items = new ArrayList<OverlayItem>();
		private Context mContext;
		
		public SiteOverlay(OverlayItem overlayItem, Drawable marker, Context context) {
			super(marker);
			mContext=context;
			
			boundCenterBottom(marker);
			
			items.add(overlayItem);

			populate();
		}

		@Override
		protected OverlayItem createItem(int i) {
			return items.get(i);
		}

		@Override
		protected boolean onTap(int i) {
			  OverlayItem item = items.get(i);
			  try {
				  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
				  dialog.setTitle(item.getTitle());
				  dialog.setMessage(item.getSnippet());
				  dialog.setNeutralButton("Details",new OnClickListener() {
				  	@Override
					public void onClick(DialogInterface dialog, int which) {
				        Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_LOCATION_DETAIL);
				        startActivity(intent);
					}
				  });
				  dialog.setNegativeButton( "Close", null);
				  dialog.show();
			  } 
			  catch (Exception e){
				  Log.e(Constants.LOGTAG, GoogleMap.CLASSTAG + " Exception showing alert", e);
			  }
			  
			  return true;
		}

		@Override
		public int size() {
			return items.size();
		}
	}
}
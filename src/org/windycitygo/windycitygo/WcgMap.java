package org.windycitygo.windycitygo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.windycitygo.windycitygo.model.Location;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class WcgMap extends TabActivity {
	private static final String CLASSTAG = WcgMap.class.getSimpleName();
	private TabHost tabHost = null;
	private ArrayList<Location> locations;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.v(Constants.LOGTAG, WcgMap.CLASSTAG + " onCreate");
        
        setContentView(R.layout.maps_main);        

    	buildTabs();
    }

	private void buildTabs() {
		
		InputStream stream = getLocationInputStream();
		
        Location wcgLocation = getLocation(stream);
        
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
    	tabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

    	Intent mapIntent = new Intent().setClass(this, GoogleMap.class);
    	mapIntent.putExtra(Constants.LOCATION_LAT_EXTRA, wcgLocation.latitude);
    	mapIntent.putExtra(Constants.LOCATION_LONG_EXTRA, wcgLocation.longitude);
    	
    	Intent floorPlanIntent =  new Intent().setClass(this, Floorplan.class);
    	floorPlanIntent.putExtra(Constants.FLOOR_PLAN_URL_EXTRA, wcgLocation.floorPlan);
    	
    	setupTab(new TextView(this), "Map", mapIntent);
    	setupTab(new TextView(this), "Floor Plan", floorPlanIntent);
	}
    
    private InputStream getLocationInputStream() {
    	Log.v(Constants.LOGTAG, WcgMap.CLASSTAG + " getLocationInputStream");
    	InputStream stream = null;
    	try {
			stream = getAssets().open("locations.xml");
		} catch (IOException e) {
            // handle
        	android.util.Log.e(Constants.LOGTAG, WcgMap.CLASSTAG + " " + e.getMessage(), e);
        }
		return stream;
    }
    
    private Location getLocation(InputStream stream) {
        Log.v(Constants.LOGTAG, WcgMap.CLASSTAG + " getLocation");
    	locations = new XmlParser().parseLocationResponse(stream);
        return locations.get(0);
    }
    
    private void setupTab(final View view, final String tag, Intent intent) {
    	Log.v(Constants.LOGTAG, WcgMap.CLASSTAG + " setupTab : " + tag);
    	View tabview = createTabView(tabHost.getContext(), tag);
    	
        TabSpec setContent = tabHost.newTabSpec(tag)
        							.setIndicator(tabview)
        							.setContent(intent);
    	tabHost.addTab(setContent);
    }

    private static View createTabView(final Context context, final String text) {
    	Log.v(Constants.LOGTAG, WcgMap.CLASSTAG + " createTabView with text : " + text);
    	View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
    	TextView tv = (TextView) view.findViewById(R.id.tabsText);
    	tv.setText(text);
    	return view;
    }
}

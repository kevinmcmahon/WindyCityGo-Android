package org.windycitygo.windycitygo;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

public class WcgMap extends TabActivity {
	private static final String CLASSTAG = WcgMap.class.getSimpleName();
	
	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.v(Constants.LOGTAG, WcgMap.CLASSTAG + " onCreate");
        
        setContentView(R.layout.maps_main);        

        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, GoogleMap.class);

        spec = tabHost.newTabSpec("map").setIndicator("Map",
                          res.getDrawable(R.drawable.ic_tab_globe))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Floorplan.class);
        spec = tabHost.newTabSpec("floorplan").setIndicator("Floorplan",
                          res.getDrawable(R.drawable.ic_tab_globe))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}

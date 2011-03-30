package org.windycitygo.windycitygo;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.v(Constants.LOGTAG, WcgMap.CLASSTAG + " onCreate");
        
        setContentView(R.layout.maps_main);        

    	tabHost = (TabHost) findViewById(android.R.id.tabhost);
    	tabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

    	setupTab(new TextView(this), "Map", new Intent().setClass(this, GoogleMap.class));
    	setupTab(new TextView(this), "Floor Plan", new Intent().setClass(this, Floorplan.class).putExtra(Constants.FLOOR_PLAN_URL_EXTRA,"http://windycitygo.org/assets/4d8ba593dabe9d6c2f00000a/floorplan_20110324.png"));
    }
    
    private void setupTab(final View view, final String tag, Intent intent) {
    	View tabview = createTabView(tabHost.getContext(), tag);
    	
        TabSpec setContent = tabHost.newTabSpec(tag)
        							.setIndicator(tabview)
        							.setContent(intent);
    	tabHost.addTab(setContent);
    }

    private static View createTabView(final Context context, final String text) {
    	View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
    	TextView tv = (TextView) view.findViewById(R.id.tabsText);
    	tv.setText(text);
    	return view;
    }
}

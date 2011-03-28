package org.windycitygo.windycitygo;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, SessionList.class);

        spec = tabHost.newTabSpec("sessions").setIndicator("Sessions",
                          res.getDrawable(R.drawable.ic_tab_clock))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, MapActivity.class);
        spec = tabHost.newTabSpec("maps").setIndicator("Maps",
                          res.getDrawable(R.drawable.ic_tab_globe))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SponsorsActivity.class);
        spec = tabHost.newTabSpec("sponsors").setIndicator("Sponsors",
                          res.getDrawable(R.drawable.ic_tab_seal))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}
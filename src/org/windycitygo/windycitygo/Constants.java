package org.windycitygo.windycitygo;

import android.content.Intent;
import android.view.Menu;

public class Constants {

    public static final String INTENT_ACTION_VIEW_SESSION_DETAIL = "org.windycitygo.windycitygo.VIEW_SESSION_DETAIL";
    public static final String INTENT_ACTION_VIEW_SESSION_LIST = "org.windycitygo.windycitygo.VIEW_SESSION_LIST";

    public static final String INTENT_ACTION_VIEW_SPONSOR_DETAIL = "org.windycitygo.windycitygo.VIEW_SPONSOR_DETAIL";
    public static final String INTENT_ACTION_VIEW_SPONSOR_LIST = "org.windycitygo.windycitygo.VIEW_SPONSOR_LIST";
    
    public static final String INTENT_ACTION_VIEW_LOCATION_DETAIL = "org.windycitygo.windycitygo.VIEW_LOCATION_DETAIL";
    
    public static final String INTENT_ACTION_VIEW_ABOUT = "org.windycitygo.windycitygo.VIEW_ABOUT_DETAIL";
    
    public static final String LOGTAG = "WindyCityGo";

    // Extras 
    public static final String STARTFROM_EXTRA = "org.windycitygo.windycitygo.StartFrom";
    public static final String FLOOR_PLAN_URL_EXTRA = "org.windycitygo.windycitygo.FloorPlanUrl";
    public static final String LOCATION_LAT_EXTRA = "org.windycitygo.windycitygo.LocationLat";
    public static final String LOCATION_LONG_EXTRA = "org.windycitygo.windycitygo.LocationLong";
	public static final String LOCATION_NAME_EXTRA = "org.windycitygo.windycitygo.LocationName";
	public static final String LOCATION_VENUE_LONG_EXTRA = "org.windycitygo.windycitygo.LocationVenueLong";
	public static final String LOCATION_VENUE_SHORT_EXTRA = "org.windycitygo.windycitygo.LocationVenueShort";
	public static final String LOCATION_ADDRESS_EXTRA = "org.windycitygo.windycitygo.LocationAddress";
	public static final String LOCATION_EXTRA = "org.windycitygo.windycitygo.Location";
	
	public static final int MENU_ID_WEBSITE = 0;
	public static final int MENU_ID_ABOUT = 1;
	public static final int MENU_ORDER_WEBSITE = Menu.FIRST;
	public static final int MENU_ORDER_ABOUT = Menu.FIRST+1;
}

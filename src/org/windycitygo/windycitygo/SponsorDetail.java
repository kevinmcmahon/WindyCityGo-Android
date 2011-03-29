package org.windycitygo.windycitygo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SponsorDetail extends Activity {

	private static final String CLASSTAG = SponsorDetail.class.getSimpleName();
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG," " + SponsorDetail.CLASSTAG + " onCreate");
        
        setContentView(R.layout.sponsor_detail);
	}
}

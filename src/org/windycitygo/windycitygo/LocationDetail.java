package org.windycitygo.windycitygo;

import org.windycitygo.windycitygo.model.Location;
import org.windycitygo.windycitygo.model.Session;
import org.windycitygo.windycitygo.util.DownloadImageTask;
import org.windycitygo.windycitygo.util.ImageHelper;
import org.windycitygo.windycitygo.util.Network;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationDetail extends Activity {
	
	private static final String CLASSTAG = LocationDetail.class.getSimpleName();
	private ImageView photoView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, LocationDetail.CLASSTAG + " onCreate");
        
        WindyCityGoApplication app = (WindyCityGoApplication) getApplication();
        final Location location = app.getCurrentLocation();
        
        setContentView(R.layout.location_detail);
        
        TextView venueView = (TextView) findViewById(R.id.venue_location_detail);
        venueView.setText(location.venueLong);
        
        String[] parts = location.address.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append(parts[0].trim() + "\n");
        sb.append(parts[1].trim() +"," + parts[2]+ "\n");
        
        TextView addressView = (TextView) findViewById(R.id.address_location_detail);
        
        addressView.setText(sb.toString());
        
        photoView = (ImageView) findViewById(R.id.picture_location_detail);
        new DownloadImageTask(photoView).execute(location.photo); 
        	
        Button directionsButton = (Button) findViewById(R.id.directions_button_location_detail);
        directionsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						Uri.parse("http://maps.google.com/maps?daddr="+ location.latitude + "," + location.longitude));
						startActivity(intent);
			}
		});
	}
}

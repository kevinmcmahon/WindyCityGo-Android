package org.windycitygo.windycitygo;

import org.windycitygo.windycitygo.model.Sponsor;
import org.windycitygo.windycitygo.util.DownloadImageTask;
import org.windycitygo.windycitygo.util.ImageHelper;
import org.windycitygo.windycitygo.util.Network;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SponsorDetail extends Activity {

	private static final String CLASSTAG = SponsorDetail.class.getSimpleName();
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG," " + SponsorDetail.CLASSTAG + " onCreate");
        
        WindyCityGoApplication app = (WindyCityGoApplication) getApplication();
        final Sponsor sponsor = app.getCurrentSponsor();
        
        setContentView(R.layout.sponsor_detail);
        
        TextView name = (TextView) findViewById(R.id.sponsor_name_detail);
        name.setText("About " + sponsor.name);
        
        TextView description = (TextView) findViewById(R.id.sponsor_description_detail);
        description.setText(sponsor.description);
        
        Button url = (Button) findViewById(R.id.sponsor_url_detail);
        url.setOnClickListener(new TextView.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(sponsor.url));
				startActivity(i);
			}
		});
       
        ImageView logo = (ImageView) findViewById(R.id.sponsor_image_detail);
        
        if(Network.isNetworkAvailable(this) && sponsor.logo != null && sponsor.logo != "") {
        	new DownloadImageTask(logo).execute(sponsor.logo);
        }
	}
}

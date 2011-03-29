package org.windycitygo.windycitygo;

import org.windycitygo.windycitygo.model.Session;
import org.windycitygo.windycitygo.util.ImageHelper;
import org.windycitygo.windycitygo.util.Network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SessionDetail extends Activity {
	private static final String CLASSTAG = SessionDetail.class.getSimpleName();
	private ImageView imgView = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG," " + SessionDetail.CLASSTAG + " onCreate");
        
        WindyCityGoApplication app = (WindyCityGoApplication) getApplication();
        Session session = app.getCurrentSession();
        
        setContentView(R.layout.session_detail);
        
        TextView titleTextView = (TextView) findViewById(R.id.title);
        TextView presenterTextView = (TextView) findViewById(R.id.presenter);
        TextView descriptionTextView = (TextView) findViewById(R.id.description);
        TextView speakerBioTextView = (TextView) findViewById(R.id.bio);
        imgView = (ImageView) findViewById(R.id.speaker_icon);
        
        titleTextView.setText(session.title);
        presenterTextView.setText(session.speaker.name);
        if(session.speaker.company != null && session.speaker.company != "")
        	presenterTextView.append(", "+session.speaker.company);
        descriptionTextView.setText(session.description);
        
        if(session.speaker.bio != null && session.speaker.bio != "") {
        	speakerBioTextView.setText(session.speaker.bio);
        }
        else {
        	TextView v = (TextView)findViewById(R.id.bio_header);
        	v.setVisibility(View.INVISIBLE);
        	speakerBioTextView.setVisibility(View.INVISIBLE);
        }
        
        if(Network.isNetworkAvailable(this) && session.speaker.headshot != null && session.speaker.headshot != "") {
        	Bitmap bitmap = Network.downloadBitmap(session.speaker.headshot); 
        	if(bitmap != null) {
        		imgView.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitmap, 12));
        	}
        }
    }
}
package org.windycitygo.windycitygo;

import org.windycitygo.windycitygo.model.Session;
import org.windycitygo.windycitygo.util.Network;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class SessionDetail extends Activity {
	private static final String CLASSTAG = SessionDetail.class.getSimpleName();
	private ImageView imgView = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG," " + SessionDetail.CLASSTAG + " onCreate ");
        
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
        descriptionTextView.setText(session.description);
        speakerBioTextView.setText(session.speaker.bio);
        if(Network.isNetworkAvailable(this)) {
        	imgView.setImageBitmap(Network.downloadBitmap(session.speaker.headshot));
        }
    }
}
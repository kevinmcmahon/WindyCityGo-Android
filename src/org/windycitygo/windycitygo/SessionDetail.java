package org.windycitygo.windycitygo;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.windycitygo.windycitygo.model.Session;
import org.windycitygo.windycitygo.util.Network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
class FlushedInputStream extends FilterInputStream {
    public FlushedInputStream(InputStream inputStream) {
    super(inputStream);
    }

    @Override
    public long skip(long n) throws IOException {
        long totalBytesSkipped = 0L;
        while (totalBytesSkipped < n) {
            long bytesSkipped = in.skip(n - totalBytesSkipped);
            if (bytesSkipped == 0L) {
                  int b = read();
                  if (b < 0) {
                      break;  // we reached EOF
                  } else {
                      bytesSkipped = 1; // we read one byte
                  }
           }
           totalBytesSkipped += bytesSkipped;
        }
        return totalBytesSkipped;
    }
}

public class SessionDetail extends Activity {
	private static final String CLASSTAG = SessionDetail.class.getSimpleName();
	private ImageView imgView = null;
	private Bitmap bmImg;
	
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
        imgView.setImageBitmap(downloadBitmap(session.speaker.headshot));
    }
	
    private Bitmap downloadBitmap(String fileUrl) {
    	  Log.v(Constants.LOGTAG," " + SessionDetail.CLASSTAG + " downloading "+ fileUrl);
          
    	  if(!Network.isNetworkAvailable(this))
    		  return null;
    	  
    	  URL myFileUrl = null;   
          Bitmap bmImg = null;
          
          try {
               myFileUrl= new URL(fileUrl);
          } catch (MalformedURLException e) {
               e.printStackTrace();
          }
          
          try {
        	  HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
        	  conn.setDoInput(true);
        	  conn.connect();
        	  int length = conn.getContentLength();
        	  InputStream is = conn.getInputStream();
              bmImg = BitmapFactory.decodeStream(new FlushedInputStream(is));
          } catch (IOException e) {
               e.printStackTrace();
          }
		return bmImg;
     }
}
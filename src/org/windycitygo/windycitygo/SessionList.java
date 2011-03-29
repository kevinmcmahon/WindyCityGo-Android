package org.windycitygo.windycitygo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.windycitygo.windycitygo.model.Session;
import org.windycitygo.windycitygo.model.SessionCategory;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class SessionList extends ListActivity {
	private static final String CLASSTAG = SessionList.class.getSimpleName();
	private ArrayList<Session> sessions;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, " " + SessionList.CLASSTAG + " onCreate");
        
        AssetManager assetManager = getAssets();
        InputStream stream = null;
        ArrayList<SessionCategory> sessionCategories = null;
        
        try {
            stream = assetManager.open("sessions.xml");
            sessionCategories = new XmlParser().parseSessionResponse(stream);
            sessions = sessionCategories.get(0).sessions;
        } catch (IOException e) {

            // handle
        	android.util.Log.e("SessionsActivity",e.getMessage());
        }
        
        this.setListAdapter(new SessionAdapter(this, R.layout.session_row, sessions));
    } 
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // set the current review to the Application (global state placed there)
        WindyCityGoApplication application = (WindyCityGoApplication) getApplication();
        application.setCurrentSession(this.sessions.get(position));

        // startFrom page is not stored in application, for example purposes it's a simple "extra"
        Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_SESSION_DETAIL);
        intent.putExtra(Constants.STARTFROM_EXTRA, getIntent().getIntExtra(Constants.STARTFROM_EXTRA, 1));
        startActivity(intent);
    }
}

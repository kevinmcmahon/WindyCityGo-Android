package org.windycitygo.windycitygo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.crypto.spec.PSource;

import org.windycitygo.windycitygo.model.Session;
import org.windycitygo.windycitygo.model.SessionCategory;

import com.commonsware.cwac.merge.MergeAdapter;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SessionList extends ListActivity {
	private static final String CLASSTAG = SessionList.class.getSimpleName();
	private ArrayList<Session> sessions;
	private MergeAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, " " + SessionList.CLASSTAG + " onCreate");
        
        AssetManager assetManager = getAssets();
        InputStream stream = null;
        ArrayList<SessionCategory> sessionCategories = null;
        adapter = new MergeAdapter();
        
        try {
            stream = assetManager.open("sessions.xml");
            sessionCategories = new XmlParser().parseSessionResponse(stream);
            sessions = sessionCategories.get(0).sessions;
        } catch (IOException e) {

            // handle
        	Log.e("SessionsActivity",e.getMessage());
        }
        
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        for (Session s : sessions) {
        	
        	View sessionRow = (View) vi.inflate(R.layout.session_row,null);
        	TextView header = (TextView) sessionRow.findViewById(R.id.session_time_list);
        	header.setText(s.startTime + " - " + s.endTime);
        	
        	TextView top = (TextView) sessionRow.findViewById(R.id.toptext);
        	TextView bot = (TextView) sessionRow.findViewById(R.id.bottomtext);
        	top.setText(s.title);
        	bot.setText(s.speaker.name);
        	if(s.speaker.company != "")
        		bot.append(", "+s.speaker.company);
        	adapter.addView(sessionRow, true); 
        }
        
        this.setListAdapter(adapter);
    } 
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Log.v(Constants.LOGTAG, " " + SessionList.CLASSTAG + " onListItemClick");
    	
    	// set the current review to the Application (global state placed there)
        WindyCityGoApplication application = (WindyCityGoApplication) getApplication();
        application.setCurrentSession(sessions.get(position));

        // startFrom page is not stored in application, for example purposes it's a simple "extra"
        Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_SESSION_DETAIL);
        intent.putExtra(Constants.STARTFROM_EXTRA, getIntent().getIntExtra(Constants.STARTFROM_EXTRA, 1));
        startActivity(intent);
    }
}

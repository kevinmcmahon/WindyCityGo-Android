package com.methodsix.windycitygo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.methodsix.windycitygo.model.Session;
import com.methodsix.windycitygo.model.SessionCategory;

import android.app.ListActivity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SessionsActivity extends ListActivity {
	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AssetManager assetManager = getAssets();
        InputStream stream = null;
        ArrayList<SessionCategory> sessions = null;
        
        try {
            stream = assetManager.open("sessions.xml");
            sessions = new XmlParser().parseSessionResponse(stream);
        } catch (IOException e) {

            // handle
        	android.util.Log.e("SessionsActivity",e.getMessage());
        }
        
        this.setListAdapter(new ArrayAdapter<Session>(this, R.layout.list_item, sessions.get(0).sessions));
    }
}

package org.windycitygo.windycitygo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.windycitygo.windycitygo.model.SponsorLevel;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class SponsorsList extends ListActivity {
	private static final String CLASSTAG = SponsorsList.class.getSimpleName();
	ArrayList<SponsorLevel> sponsorLevels = null;
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Log.v(Constants.LOGTAG, " " + SponsorsList.CLASSTAG + " onCreate");
	        
	        AssetManager assetManager = getAssets();
	        InputStream stream = null;
	
	        try {
	            stream = assetManager.open("sponsors.xml");
	            sponsorLevels = new XmlParser().parseSponsorResponse(stream);
	        } catch (IOException e) {
	            // handle
	        	android.util.Log.e("SessionsActivity",e.getMessage());
	        }
	        
	        for (SponsorLevel level : sponsorLevels)
	        {
	        	adapter.addSection(level.name, new SponsorAdapter(this,R.layout.sponsor_row,level.sponsors));
	        }
	        
	        this.setListAdapter(adapter);
	   }
	   
	   SectionedAdapter adapter = new SectionedAdapter() {

			@Override
			protected View getHeaderView(String caption, 
											int index,
											View convertView,
											ViewGroup parent) {
				TextView result=(TextView)convertView;
				
				if (convertView==null) {
					result=(TextView)getLayoutInflater().inflate(R.layout.header,null);
				}
				
				result.setText(caption);
				
				return(result);
			}
		};
		
		@Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
		
			// set the current review to the Application (global state placed there)
	        WindyCityGoApplication application = (WindyCityGoApplication) getApplication();
	        application.setCurrentSponsor(null);
	        
	        // startFrom page is not stored in application, for example purposes it's a simple "extra"
	        Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_SPONSOR_DETAIL);
	        intent.putExtra(Constants.STARTFROM_EXTRA, getIntent().getIntExtra(Constants.STARTFROM_EXTRA, 1));
	        startActivity(intent);
	    }
}

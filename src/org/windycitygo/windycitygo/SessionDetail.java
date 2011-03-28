package org.windycitygo.windycitygo;

import org.windycitygo.windycitygo.model.Session;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SessionDetail extends Activity {
	private static final String CLASSTAG = SessionDetail.class.getSimpleName();
 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        WindyCityGoApplication app = (WindyCityGoApplication) getApplication();
        Session session = app.getCurrentSession();
        
        TextView tv = new TextView(this);
        tv.setText(session.description);
        setContentView(tv);
    }
}

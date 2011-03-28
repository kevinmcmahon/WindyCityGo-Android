package org.windycitygo.windycitygo;

import org.windycitygo.windycitygo.model.Session;

import android.app.Application;

public class WindyCityGoApplication extends Application {

	private Session session;
	
	public WindyCityGoApplication() {
		super();
	}
	
	public Session getCurrentSession() {
		return session;
	}
	
	public void setCurrentSession(Session currentSession) {
		this.session = currentSession;
	}
}

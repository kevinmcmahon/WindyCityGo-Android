package org.windycitygo.windycitygo;

import org.windycitygo.windycitygo.model.Location;
import org.windycitygo.windycitygo.model.Session;
import org.windycitygo.windycitygo.model.Sponsor;
import org.windycitygo.windycitygo.model.SponsorLevel;

import android.app.Application;

public class WindyCityGoApplication extends Application {
	
	private Session session;
	private Sponsor sponsor;
	private Location location;
	
	public WindyCityGoApplication() {
		super();
	}
	
	public Session getCurrentSession() {
		return session;
	}
	
	public void setCurrentSession(Session currentSession) {
		this.session = currentSession;
	}

	public void setCurrentSponsor(Sponsor currentSponsor) {
		this.sponsor = currentSponsor;	
	}
	
	public Sponsor getCurrentSponsor(){
		return this.sponsor;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.location = currentLocation;
	}
	public Location getCurrentLocation() {
		return this.location;
	}
}

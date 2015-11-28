package com.kd8itx.plaguester.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class LatLong {
	@Id
	private ObjectId id;
	private ObjectId personId;
	private String lat;
	private String lon;
	
	@Override public String toString() {
		return "PersonId ["+this.personId+"], Lat ["+this.lat+"], Long ["+this.lon+"]";
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	public ObjectId getPersonId() {
		return personId;
	}
	public void setPersonId(ObjectId personId) {
		this.personId = personId;
	}
	
	
}

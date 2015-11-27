package com.kd8itx.plaugester.domain;


public class LatLong {
	private String email;
	private String lat;
	private String lon;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	
	@Override public String toString() {
		return "Email ["+this.email+"], Lat ["+this.lat+"], Long ["+this.lon+"]";
	}
	
	
}

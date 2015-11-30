package com.kd8itx.plaguester.domain;

import java.math.BigDecimal;
import java.util.Date;

public class LatLong {
	
	private int lat;
	private int lon;
	private Date time;
	
	public LatLong() {}
	
	public LatLong(String lat, String lon, Date time) {
		// Go with 5 decimal places, this should give 1 meter resolution
		// http://stackoverflow.com/questions/15965166/what-is-the-maximum-length-of-latitude-and-longitude
		BigDecimal latBD = new BigDecimal(lat);
		latBD = latBD.movePointRight(5);
		this.lat = latBD.intValue();
		
		BigDecimal lonBD = new BigDecimal(lon);
		lonBD = lonBD.movePointRight(5);
		this.lon = lonBD.intValue();
		
		this.time = time;
	}
	
	@Override public String toString() {
		return "Time ["+this.time+"], Lat ["+this.lat+"], Long ["+this.lon+"]";
	}
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}
	
	
}

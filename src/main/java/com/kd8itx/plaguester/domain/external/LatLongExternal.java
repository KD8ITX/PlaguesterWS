package com.kd8itx.plaguester.domain.external;

import java.util.Date;

import com.kd8itx.plaguester.domain.LatLong;

public class LatLongExternal {
	private String lat;
	private String lon;
	private Date time;
	
	public LatLongExternal() {}
	
	public LatLongExternal(LatLong latLong) {
		this.lat = Integer.toString(latLong.getLat());
		this.lon = Integer.toString(latLong.getLon());
		this.time = latLong.getTime();
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}

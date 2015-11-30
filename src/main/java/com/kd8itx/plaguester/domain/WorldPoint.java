package com.kd8itx.plaguester.domain;

public class WorldPoint {

	private int lat;
	private int lon;
	private int tileId; // What tile to show
	
	
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
	public int getTileId() {
		return tileId;
	}
	public void setTileId(int tileId) {
		this.tileId = tileId;
	}
}

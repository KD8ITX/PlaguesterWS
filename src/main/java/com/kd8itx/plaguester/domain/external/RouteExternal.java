package com.kd8itx.plaguester.domain.external;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.kd8itx.plaguester.domain.LatLong;
import com.kd8itx.plaguester.domain.Route;

public class RouteExternal {
	private String id;
	private List<LatLongExternal> points;
	private Date startTime;
	private Date endTime;
	
	public RouteExternal() {}
	
	public RouteExternal(Route route) {
		this.endTime = route.getEndTime();
		if (route.getId() != null) {
			this.id = route.getId().toString();
		}
		this.startTime = route.getStartTime();
		
		List<LatLongExternal> points = new ArrayList<LatLongExternal>();
		for (LatLong latLong : route.getPoints()) {
			points.add(new LatLongExternal(latLong));
		}
		
		// Add the points to the object if there were any in the list, otherwise leave null
		if (points.isEmpty() == false) {
			this.points = points;
		}
	}
	
	public Route backendRoute(ObjectId personId) {
		Route route = new Route();
		route.setEndTime(this.endTime);
		if (this.id != null) {
			route.setId(new ObjectId(this.id));
		}
		route.setPersonId(personId);
		route.setStartTime(this.startTime);
		
		List<LatLong> internalPoints = new ArrayList<LatLong>();
		for (LatLongExternal latLong : this.points) {
			internalPoints.add(new LatLong(latLong.getLat(), latLong.getLon(), latLong.getTime()));
		}
		if (internalPoints.isEmpty() == false) {
			route.setPoints(internalPoints);
		}
		
		return route;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<LatLongExternal> getPoints() {
		return points;
	}
	public void setPoints(List<LatLongExternal> points) {
		this.points = points;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}

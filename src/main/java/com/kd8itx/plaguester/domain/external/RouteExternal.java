package com.kd8itx.plaguester.domain.external;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kd8itx.plaguester.domain.LatLong;
import com.kd8itx.plaguester.domain.Route;

public class RouteExternal {
	private String id;
	private String personId;
	private List<LatLongExternal> points;
	private Date startTime;
	private Date endTime;
	
	public RouteExternal() {}
	
	public RouteExternal(Route route) {
		this.endTime = route.getEndTime();
		if (route.getId() != null) {
			this.id = route.getId().toString();
		}
		if (route.getPersonId() != null) {
			this.personId = route.getPersonId().toString();
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
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

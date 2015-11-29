package com.kd8itx.plaguester.domain;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Route")
public class Route {
	
	@Id
	private ObjectId id;
	private ObjectId personId;
	private List<LatLong> points;
	private Date startTime;
	private Date endTime;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public ObjectId getPersonId() {
		return personId;
	}
	public void setPersonId(ObjectId personId) {
		this.personId = personId;
	}
	public List<LatLong> getPoints() {
		return points;
	}
	public void setPoints(List<LatLong> points) {
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

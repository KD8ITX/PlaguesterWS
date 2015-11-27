package com.kd8itx.plaugester.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class User {
	
	@Id
	private ObjectId userId;
	private String username;
	
	
	public ObjectId getUserId() {
		return userId;
	}
	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}

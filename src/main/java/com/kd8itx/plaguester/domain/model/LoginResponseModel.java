package com.kd8itx.plaguester.domain.model;

import java.util.List;

import com.kd8itx.plaguester.domain.Person;

public class LoginResponseModel {
	private String userId;
	private String sessionId;
	private List<Person> people;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}
}

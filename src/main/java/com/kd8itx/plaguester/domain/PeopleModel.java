package com.kd8itx.plaguester.domain;

import java.util.List;

import com.kd8itx.plaguester.domain.external.PersonExternal;

public class PeopleModel {
	private List<PersonExternal> people;

	public List<PersonExternal> getPeople() {
		return people;
	}

	public void setPeople(List<PersonExternal> people) {
		this.people = people;
	}
}

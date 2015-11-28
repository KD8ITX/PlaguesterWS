package com.kd8itx.plaguester.domain.external;

import java.util.Date;

import org.bson.types.ObjectId;

import com.kd8itx.plaguester.domain.Gender;
import com.kd8itx.plaguester.domain.Person;

public class PersonExternal {
	private String id;
	private String userId;
	private Gender gender;
	private Date birthDate;
	private String name;
	private String fatherId;
	private String motherId;
	
	public PersonExternal() {}
	
	public PersonExternal(Person person) {
		this.id = person.getId().toString();
		this.userId = person.getUserId().toString();
		this.gender = person.getGender();
		this.birthDate = person.getBirthDate();
		this.name = person.getName();
		if (person.getFatherId() != null) {
			this.fatherId = person.getFatherId().toString();
		}
		if (person.getMotherId() != null) {
			this.motherId = person.getMotherId().toString();
		}
	}
	
	public Person getPerson() {
		Person person = new Person();
		person.setBirthDate(this.birthDate);
		if (this.fatherId != null) {
			person.setFatherId(new ObjectId(this.fatherId));
		}
		person.setGender(this.gender);
		if (this.id != null) {
			person.setId(new ObjectId(this.id));
		}
		if (this.motherId != null) {
			person.setMotherId(new ObjectId(this.motherId));
		}
		person.setName(this.name);
		if (this.userId != null) {
			person.setUserId(new ObjectId(this.userId));
		}
		
		return person;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	public String getMotherId() {
		return motherId;
	}
	public void setMotherId(String motherId) {
		this.motherId = motherId;
	}
	
}

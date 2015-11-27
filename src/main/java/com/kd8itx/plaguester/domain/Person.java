package com.kd8itx.plaguester.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity("Person")
public class Person {
	@Id
	private ObjectId id;
	private ObjectId userId;
	
	private Gender gender;
	private Date birthDate;
	private String name;
	private ObjectId fatherId;
	private ObjectId motherId;
	
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public ObjectId getUserId() {
		return userId;
	}
	public void setUserId(ObjectId userId) {
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
	public ObjectId getFatherId() {
		return fatherId;
	}
	public void setFatherId(ObjectId fatherId) {
		this.fatherId = fatherId;
	}
	public ObjectId getMotherId() {
		return motherId;
	}
	public void setMotherId(ObjectId motherId) {
		this.motherId = motherId;
	}
	
	
}

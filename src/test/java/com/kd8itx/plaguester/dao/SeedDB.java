package com.kd8itx.plaguester.dao;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.mongodb.morphia.dao.BasicDAO;

import com.kd8itx.palguester.domain.Gender;
import com.kd8itx.palguester.domain.Person;
import com.kd8itx.plaugester.dao.MongoAccessor;
import com.kd8itx.plaugester.dao.PersonDAO;

public class SeedDB {
	
	private static BasicDAO<Person, ObjectId> personDAO = MongoAccessor.createDAO(Person.class);
	
	@Test
	public void testMongoConnectivity() {
		
		Person person = new Person();
		person.setGender(Gender.MALE);
		person.setName("Bubba");
		PersonDAO.create(person);
		
	  
	}
}

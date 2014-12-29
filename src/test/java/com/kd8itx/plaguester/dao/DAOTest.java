package com.kd8itx.plaguester.dao;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.dao.BasicDAO;

import com.kd8itx.palguester.domain.Person;
import com.kd8itx.plaugester.dao.MongoAccessor;
import com.kd8itx.plaugester.dao.PersonDAO;

public class DAOTest {
	private static BasicDAO<Person, ObjectId> personDAO = MongoAccessor.createDAO(Person.class);
	
	@Test
	public void testMongoConnectivity() {
		int numberOfPeople = PersonDAO.getAll().size();
		
		Assert.assertTrue(numberOfPeople > 0);
	}
	
	@Test
	public void testPersonCrud() {
		Person testPerson = new Person();
		testPerson.setName("test1");
		
		// Create
		ObjectId originalPersonId = PersonDAO.create(testPerson);
		Assert.assertNotNull("Create ObjectId null", originalPersonId);
		
		// Retrieve
		Person retrievePerson = PersonDAO.get(originalPersonId);
		Assert.assertEquals("Name does not match", testPerson.getName(), retrievePerson.getName());
		
		// Change a value
		retrievePerson.setName("test2");
		
		// Update (Upsert)
		ObjectId updatedPersonId = PersonDAO.create(retrievePerson);
		Assert.assertEquals("Did not update, created new record", originalPersonId, updatedPersonId);
		
		
		// Delete
		boolean deletedCorrectly = PersonDAO.delete(updatedPersonId);
		Assert.assertTrue(deletedCorrectly);
	}
	
}

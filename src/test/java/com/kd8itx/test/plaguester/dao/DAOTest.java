package com.kd8itx.test.plaguester.dao;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;

import com.kd8itx.plaguester.dao.PersonDAO;
import com.kd8itx.plaguester.dao.UserDAO;
import com.kd8itx.plaguester.domain.Person;
import com.kd8itx.plaguester.domain.User;

public class DAOTest {
	
	
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

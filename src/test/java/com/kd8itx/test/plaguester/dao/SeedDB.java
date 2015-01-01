package com.kd8itx.test.plaguester.dao;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.kd8itx.plaguester.dao.PersonDAO;
import com.kd8itx.plaguester.dao.UserDAO;
import com.kd8itx.plaguester.domain.DNA;
import com.kd8itx.plaguester.domain.DNAType;
import com.kd8itx.plaguester.domain.Gender;
import com.kd8itx.plaguester.domain.Person;
import com.kd8itx.plaguester.domain.User;

public class SeedDB {
	
	@Ignore
	@Test
	public void testMongoConnectivity() {
		
		Person person = new Person();
		person.setGender(Gender.MALE);
		person.setName("Bubba");
		PersonDAO.create(person); 
	}
	
	@Test
	public void initDB() {
		clearDb();
		ObjectId rootUserId = createRootUser();
		createAdamAndEve(rootUserId);
	}
	
	private void clearDb() {
		UserDAO.deleteAll();
		PersonDAO.deleteAll();
	}
	
	private ObjectId createRootUser() {
		User rootUser = new User();
		rootUser.setUsername("mweidner");
		
		ObjectId rootUserId = UserDAO.create(rootUser);
		Assert.assertNotNull("Create ObjectId null", rootUserId);
		
		return rootUserId;
	}
	
	
	private void createAdamAndEve(ObjectId userId) {
		
		DNA[] adamDna = new DNA[DNAType.values().length];
		int i = 0;
		for (DNAType dnaType : DNAType.values()) {
			adamDna[i] = new DNA(dnaType);
			i++;
		}
		Person adam = new Person(userId, Gender.MALE, "Adam", null, null, adamDna);
		
		
		DNA[] eveDna = new DNA[DNAType.values().length];
		i = 0;
		for (DNAType dnaType : DNAType.values()) {
			eveDna[i] = new DNA(dnaType);
			i++;
		}
		Person eve = new Person(userId, Gender.FEMALE, "Eve", null, null, eveDna);
		
		
		PersonDAO.create(adam);
		PersonDAO.create(eve);
	}
}

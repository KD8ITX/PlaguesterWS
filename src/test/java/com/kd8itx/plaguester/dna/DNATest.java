package com.kd8itx.plaguester.dna;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.kd8itx.palguester.domain.Gender;
import com.kd8itx.palguester.domain.Person;
import com.kd8itx.plaugester.dao.PersonDAO;

public class DNATest {

	@Ignore
	@Test
	public void createAdamAndEve() {
		Person adam = new Person();
		adam.setName("Adam");
		adam.setGender(Gender.MALE);
		
		Person eve = new Person();
		eve.setName("Eve");
		eve.setGender(Gender.FEMALE);
		
		PersonDAO.create(adam);
		PersonDAO.create(eve);
	}
	
	
	@Test
	public void testDnaCreation() throws Exception {
		
		Person.createNewDNA(new ObjectId("54a4b0f444ae7776f15b025e"), new ObjectId("54a4b0f444ae7776f15b025f"));
	}
	
	
	
}

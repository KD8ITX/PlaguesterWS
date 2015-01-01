package com.kd8itx.test.plaguester.dna;

import org.bson.types.ObjectId;
import org.junit.Ignore;
import org.junit.Test;

import com.kd8itx.plaguester.dao.PersonDAO;
import com.kd8itx.plaguester.domain.DNA;
import com.kd8itx.plaguester.domain.DNAType;
import com.kd8itx.plaguester.domain.Gender;
import com.kd8itx.plaguester.domain.Person;

public class DNATest {

	
	
	
	@Test
	public void testDnaCreation() throws Exception {
		
		Person.createNewDNA(new ObjectId("54a4b0f444ae7776f15b025e"), new ObjectId("54a4b0f444ae7776f15b025f"));
	}
	
	
	
}

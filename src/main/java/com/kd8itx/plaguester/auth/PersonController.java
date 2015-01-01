package com.kd8itx.plaguester.auth;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd8itx.palguester.domain.Person;
import com.kd8itx.plaugester.dao.PersonDAO;

@RestController
public class PersonController {
	
	@RequestMapping("/Person/Create")
    public String Create(@RequestBody Person passedInPerson) {
		ObjectId personId = PersonDAO.create(passedInPerson);
		
		return personId.toString();
    }
	
	@RequestMapping("/Person/Get")
    public Person Get(@RequestBody String personId) {
		Person person = PersonDAO.get(new ObjectId(personId));
		
		return person;
    }
	
	@RequestMapping("/Person/Update")
    public String Update(@RequestBody Person passedInPerson) {
		ObjectId personId = PersonDAO.create(passedInPerson);
		
		return personId.toString();
    }
	
	@RequestMapping("/Person/Delete")
    public boolean Delete(@RequestBody String personId) {
		boolean deleteSuccessfull = PersonDAO.delete(new ObjectId(personId));
		
		return deleteSuccessfull;
    }
	
}

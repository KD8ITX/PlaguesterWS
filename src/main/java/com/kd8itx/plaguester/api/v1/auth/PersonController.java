package com.kd8itx.plaguester.api.v1.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kd8itx.plaguester.dao.PersonDAO;
import com.kd8itx.plaguester.domain.Person;
import com.kd8itx.plaguester.domain.external.PersonExternal;
import com.kd8itx.plaguester.domain.model.PeopleModel;
import com.kd8itx.plaguester.exception.LoginException;
import com.kd8itx.security.UserSession;

@RestController
public class PersonController {
	Logger log = LoggerFactory.getLogger(PersonController.class);
	
	@RequestMapping(value = "/V1/Person/Create", method = RequestMethod.POST)
    public PersonExternal Create(@RequestBody PersonExternal personPassed, HttpServletRequest request) throws LoginException {
		UserSession session = UserSession.validate(request, false);
		
		// Convert from external objects to internal
		Person person = personPassed.backendPerson();
		
		// Override passed in values with trusted ones
		person.setUserId(session.getUserId());
		person.setId(null);
		person.setBirthDate(new Date());

		// Create person, return back created ID
		ObjectId personId = PersonDAO.createPerson(person);
		
		// Query to get back Person based off from returned ID
		Person newPerson = PersonDAO.get(personId);
		PersonExternal personReturned = new PersonExternal(newPerson); 
		
		return personReturned;
    }
	
	@RequestMapping(value = "/V1/Person/GetAll", method = RequestMethod.POST)
    public PeopleModel GetAll(HttpServletRequest request) throws Exception {
		UserSession session = UserSession.validate(request, false);
		
		List<Person> peopleDb = PersonDAO.getAll(session.getUserId());
		
		// Loop over the people returned from the DB and convert them to external objects
		List<PersonExternal> people = new ArrayList<PersonExternal>();
		for (Person person : peopleDb) {
			people.add(new PersonExternal(person));
		}
		
		PeopleModel model = new PeopleModel();
		model.setPeople(people);
		return model;
    }
	
	@RequestMapping(value = "/V1/Person/Delete", method = RequestMethod.POST)
	// TODO: This method is a test method that will need removed later (used to keep DB clean with lots of jMeter usage)
    public Boolean Delete(@RequestBody PersonExternal person, HttpServletRequest request) throws Exception {
		UserSession session = UserSession.validate(request, false);
		ObjectId deletePersonId = new ObjectId(person.getId());
		
		Person newPerson = PersonDAO.get(deletePersonId);
		
		// Make sure this person is tied to this user
		if (newPerson == null || newPerson.getUserId().equals(session.getUserId()) == false) {
			throw new SecurityException("You don't have rights to delete Person ["+person.getId()+"]");
		}
		
		PersonDAO.delete(deletePersonId);
		log.info("Deleted Person ["+person.getId()+"]");
		
		return true;
    }
	
	@RequestMapping(value = "/V1/Person/Select", method = RequestMethod.POST)
    public Boolean Select(@RequestBody PersonExternal person, HttpServletRequest request) throws Exception {
		UserSession session = UserSession.validate(request, false);
		ObjectId selectPersonId = new ObjectId(person.getId());
		
		Person newPerson = PersonDAO.get(selectPersonId);
		
		// Make sure this person is tied to this user
		if (newPerson == null || newPerson.getUserId().equals(session.getUserId()) == false) {
			throw new SecurityException("You don't have rights to select Person ["+person.getId()+"]");
		}
		
		// Set the personId into session
		request.getSession().setAttribute("PersonId", newPerson.getId());
		
		return true;
    }
}

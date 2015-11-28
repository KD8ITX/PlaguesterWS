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
import com.kd8itx.plaguester.domain.PeopleModel;
import com.kd8itx.plaguester.domain.Person;
import com.kd8itx.plaguester.domain.external.PersonExternal;
import com.kd8itx.plaguester.exception.LoginException;
import com.kd8itx.security.UserSession;

@RestController
public class PersonController {
	Logger log = LoggerFactory.getLogger(PersonController.class);
	
	@RequestMapping(value = "/V1/Person/Create", method = RequestMethod.POST)
    public Person Create(@RequestBody PersonExternal personPassed, HttpServletRequest request) throws LoginException {
		ObjectId userId = UserSession.validate(request);
		
		// Convert from external objects to internal
		Person person = personPassed.getPerson();
		
		// Override passed in values with trusted ones
		person.setUserId(userId);
		person.setId(null);
		person.setBirthDate(new Date());

		PersonDAO.createPerson(person);
		
		return person;
    }
	
	@RequestMapping(value = "/V1/Person/GetAll", method = RequestMethod.POST)
    public PeopleModel GetAll(HttpServletRequest request) throws Exception {
		ObjectId userId = UserSession.validate(request);
		
		List<Person> peopleDb = PersonDAO.getAll(userId);
		
		// Loop over the people returned from the DB and convert them to external objects
		List<PersonExternal> people = new ArrayList<PersonExternal>();
		for (Person person : peopleDb) {
			people.add(new PersonExternal(person));
		}
		
		PeopleModel model = new PeopleModel();
		model.setPeople(people);
		return model;
    }
}

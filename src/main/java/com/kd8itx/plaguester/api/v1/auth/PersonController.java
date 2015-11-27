package com.kd8itx.plaguester.api.v1.auth;

import java.util.List;

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

@RestController
public class PersonController {
	Logger log = LoggerFactory.getLogger(PersonController.class);
	
	@RequestMapping(value = "/V1/Person/Create", method = RequestMethod.POST)
    public Person Create(@RequestBody Person person) {
		//log.debug(person2.toString());
		// TODO: Verify that the parameters coming in are actually for this user
		PersonDAO.createPerson(person);
		
		return person;
    }
	
	@RequestMapping(value = "/V1/Person/GetAll", method = RequestMethod.POST)
    public PeopleModel GetAll(@RequestBody Person userId) throws Exception {
		//log.debug(person2.toString());
		// TODO: Verify that the parameters coming in are actually for this user
		List<Person> people = PersonDAO.getAll(userId.getUserId());
		
		PeopleModel model = new PeopleModel();
		model.setPeople(people);
		return model;
    }
}

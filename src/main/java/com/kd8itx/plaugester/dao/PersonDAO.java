package com.kd8itx.plaugester.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.kd8itx.palguester.domain.Person;


public class PersonDAO {
	
	private static BasicDAO<Person, ObjectId> personDAO = MongoAccessor.createDAO(Person.class);
	
	public PersonDAO() {}
	
	public static boolean createPerson (Person person) {
        personDAO.save(person);
        return true;
    }
	
public static List<Person> getAll() {
        List<Person> people = personDAO.find().asList();
        
        return people;
    }
}

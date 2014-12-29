package com.kd8itx.plaugester.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.kd8itx.palguester.domain.Person;


public class PersonDAO {
	
	private static BasicDAO<Person, ObjectId> personDAO = MongoAccessor.createDAO(Person.class);
	
	public PersonDAO() {}
	
	public static ObjectId create (Person person) {
        ObjectId id = (ObjectId)personDAO.save(person).getId();
        return id;
    }
	
	public static Person get(ObjectId id) {
		Person person = personDAO.get(id);
		return person;
	}
	
	public static void update(Person person) {
	//	personDAO.createQuery().field("id").equal(person.getId());
		
	//	personDAO.up
		
		//personDAO.createQuery(Person.class); //(Person.class).field(Mapper.ID_KEY).equal(person.getId());
		//personDAO.update()
	}
	
	public static boolean delete(ObjectId id) {
		personDAO.deleteById(id);
		
		// Not sure what to check here, lets just say its all good
		return true;
	}
	
	public static List<Person> getAll() {
        List<Person> people = personDAO.find().asList();
        
        return people;
    }
}

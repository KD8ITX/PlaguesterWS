package com.kd8itx.plaguester.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.kd8itx.plaguester.domain.Person;
import com.kd8itx.plaguester.domain.User;


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
		Query<Person> abc = personDAO.createQuery().field("id").notEqual("abc");
		personDAO.deleteByQuery(abc);
	//	personDAO.up
		
		//personDAO.createQuery(Person.class); //(Person.class).field(Mapper.ID_KEY).equal(person.getId());
		//personDAO.update()
	}
	
	public static boolean delete(ObjectId id) {
		personDAO.deleteById(id);
		
		// Not sure what to check here, lets just say its all good
		return true;
	}
	
	public static boolean deleteAll() {
		Query<Person> abc = personDAO.createQuery().field("id").notEqual("abc");
		personDAO.deleteByQuery(abc);
		
		return true;
	}
	
	public static List<Person> getAll() {
        List<Person> people = personDAO.find().asList();
        
        return people;
    }
}

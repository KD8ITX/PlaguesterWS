package com.kd8itx.plaguester.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import com.kd8itx.plaguester.domain.Person;


public class PersonDAO {
	
	private static BasicDAO<Person, ObjectId> personDAO = MongoAccessor.createDAO(Person.class);
	
	public PersonDAO() {}
	
	public static boolean createPerson (Person person) {
        personDAO.save(person);
        return true;
    }
	
public static List<Person> getAll(ObjectId userId) throws Exception {
	
	if (userId == null) {
		throw new Exception("Missing userId");
	}
	
	Query<Person> q = personDAO.createQuery();
	q.field("userId").equal(userId);
	
	List<Person> results = personDAO.find(q).asList();
	    
    return results;
    }
}

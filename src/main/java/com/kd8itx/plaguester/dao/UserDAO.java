package com.kd8itx.plaguester.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.kd8itx.plaguester.domain.Person;
import com.kd8itx.plaguester.domain.User;

public class UserDAO {

	private static BasicDAO<User, ObjectId> userDAO = MongoAccessor.createDAO(User.class);
	
	public UserDAO() {}
	
	public static ObjectId create (User user) {
        ObjectId id = (ObjectId)userDAO.save(user).getId();
        return id;
    }
	
	public static User get(ObjectId id) {
		User user = userDAO.get(id);
		return user;
	}
	
	public static boolean delete(ObjectId id) {
		userDAO.deleteById(id);
		
		// Not sure what to check here, lets just say its all good
		return true;
	}
	
	public static boolean deleteAll() {
		Query<User> abc = userDAO.createQuery().field("id").notEqual("abc");
		userDAO.deleteByQuery(abc);
		
		return true;
	}
	
	public static List<User> getAll() {
        List<User> users = userDAO.find().asList();
        
        return users;
    }
}

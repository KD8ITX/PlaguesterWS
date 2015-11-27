package com.kd8itx.plaguester.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.kd8itx.plaguester.domain.User;

public class UserDAO {
private static BasicDAO<User, ObjectId> userDAO = MongoAccessor.createDAO(User.class);
		
		public UserDAO() {}
		
		public static User getByUsername(String username) {
			User user = userDAO.findOne("username", username);
			
			return user;
		}
		
		public static boolean createPerson (User user) {
	        userDAO.save(user);
	        return true;
	    }
		
		public static List<User> getAll() {
	        List<User> users = userDAO.find().asList();
	        
	        return users;
	    }

}

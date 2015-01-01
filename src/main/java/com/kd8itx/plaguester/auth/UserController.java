package com.kd8itx.plaguester.auth;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd8itx.palguester.domain.User;
import com.kd8itx.plaugester.dao.UserDAO;

@RestController
public class UserController {

	@RequestMapping("/User/Create")
    public String Create(@RequestBody User passedInUser) {
		ObjectId userId = UserDAO.create(passedInUser);
		
		return userId.toString();
    }
	
	@RequestMapping("/User/Get")
    public User Get(@RequestBody String userId) {
		User user = UserDAO.get(new ObjectId(userId));
		
		return user;
    }
	
	@RequestMapping("/User/Update")
    public String Update(@RequestBody User passedInUser) {
		ObjectId userId = UserDAO.create(passedInUser);
		
		return userId.toString();
    }
	
	@RequestMapping("/User/Delete")
    public boolean Delete(@RequestBody String userId) {
		boolean deleteSuccessfull = UserDAO.delete(new ObjectId(userId));
		
		return deleteSuccessfull;
    }

}

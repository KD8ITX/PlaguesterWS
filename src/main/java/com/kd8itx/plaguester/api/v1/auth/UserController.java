package com.kd8itx.plaguester.api.v1.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kd8itx.plaguester.dao.PersonDAO;
import com.kd8itx.plaguester.dao.UserDAO;
import com.kd8itx.plaguester.domain.Person;
import com.kd8itx.plaguester.domain.User;
import com.kd8itx.plaguester.domain.model.LoginResponseModel;



@RestController
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/V1/User/Login", method = RequestMethod.POST)
    public ResponseEntity<?> Login(@RequestBody User passedInUser, HttpServletRequest request) throws Exception {
        
		// Clear out the existing user session for security
		request.getSession().invalidate();
		
		if (passedInUser == null || StringUtils.isBlank(passedInUser.getUsername())) {
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
		}
		
		
		// Login user
		User user = UserDAO.getByUsername(passedInUser.getUsername());
		if (user != null) {
			
		} else {
			// TODO: Move this out of here into an actual real create routine
			//UserDAO.createPerson(passedInUser);
			//user = UserDAO.getByUsername(passedInUser.getUsername());
			//log.warn("FYI, we just created this user ["+passedInUser.getUsername()+"], this is just here for debugging purposes");
		}
		
		// Get "People" assigned to this user
		List<Person> people = PersonDAO.getAll(user.getUserId());
		
		// Set the userId into session
		request.getSession().setAttribute("UserId", user.getUserId());
		
		
		LoginResponseModel model = new LoginResponseModel();
		model.setUserId(user.getUserId().toString());
		model.setSessionId(request.getSession().getId());
		model.setPeople(people);
		return new ResponseEntity<>(model, HttpStatus.OK);
    }
	
	@RequestMapping("/V1/User/Logout")
    public Boolean Logout(HttpServletRequest request) {
        
		// Kill off the current session
		request.getSession().invalidate();
		
		return true;
    }
}

package com.kd8itx.plaguester.auth;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kd8itx.palguester.domain.Person;
import com.kd8itx.palguester.domain.User;
import com.kd8itx.plaugester.dao.PersonDAO;

@RestController
public class UserController {

	@RequestMapping("/User/Login")
    public User greeting(@RequestParam(value="name", defaultValue="Guest") String name) {
        
		User user = new User();
		user.setUsername(name);
		user.setGreeting("Greetings potential victim");
		
		return user;
    }
	
	@RequestMapping("/User/Logout")
    public User Logout(@RequestBody User passedInUser) {
        
		User user = new User();
		user.setUsername(passedInUser.getUsername());
		user.setGreeting("Greetings potential victim");
		
		return user;
    }
	
	@RequestMapping("/Person/Create")
    public Person Create(@RequestBody Person passedInPerson) {
		PersonDAO.createPerson(passedInPerson);
		List<Person> people = PersonDAO.getAll();
		
		return passedInPerson;
    }
	
}

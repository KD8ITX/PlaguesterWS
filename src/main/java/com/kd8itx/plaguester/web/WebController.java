package com.kd8itx.plaguester.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kd8itx.plaguester.dao.PersonDAO;
import com.kd8itx.plaguester.domain.Person;

@Controller
public class WebController {

	@RequestMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
	
	@RequestMapping("/map")
    public String map() {
        return "map";
    }
	
	@RequestMapping("/family")
    public ModelAndView family(HttpServletRequest request) throws Exception {
		
		ObjectId userId = (ObjectId)request.getSession().getAttribute("UserId");
		List<Person> people = PersonDAO.getAll(userId);
		
		ModelAndView mav = new ModelAndView("family") ;
        
		/*
		List<Person> people = new ArrayList<Person>();
        Person person1 = new Person();
        person1.setName("Adam");
        person1.setGender(Gender.MALE);
        
        Person person2 = new Person();
        person2.setName("Eve");
        person2.setGender(Gender.FEMALE);
        
        people.add(person1);
        people.add(person2);
        */
        mav.addObject("people", people);  
        return mav;
    }
	
	@RequestMapping("/family/add")
    public String family_add() {
        return "family_add";
    }
	
}

package com.kd8itx.security;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;

import com.kd8itx.plaguester.exception.LoginException;

public class UserSession {
	
	private ObjectId userId;
	private ObjectId personId;
	
	public static UserSession validate(HttpServletRequest request, Boolean requirePerson) throws LoginException {
		ObjectId userId = (ObjectId)request.getSession().getAttribute("UserId");
		ObjectId personId = (ObjectId)request.getSession().getAttribute("PersonId");
		
		UserSession userSession = new UserSession();
		
		if (userId == null) {
			throw new LoginException("No valid userId in session");
		} else {
			userSession.userId = userId;
		}
		
		if (personId == null && requirePerson == true) {
			throw new SecurityException("No Person set");
		} else {
			userSession.personId = personId;
		}
		
		return userSession;
	}

	public ObjectId getUserId() {
		return userId;
	}

	public ObjectId getPersonId() {
		return personId;
	}
}

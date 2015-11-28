package com.kd8itx.security;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;

import com.kd8itx.plaguester.exception.LoginException;

public class UserSession {
	
	public static ObjectId validate(HttpServletRequest request) throws LoginException {
		ObjectId userId = (ObjectId)request.getSession().getAttribute("UserId");
		
		if (userId == null) {
			throw new LoginException("No valid userId in session");
		}
		
		return userId;
	}
}

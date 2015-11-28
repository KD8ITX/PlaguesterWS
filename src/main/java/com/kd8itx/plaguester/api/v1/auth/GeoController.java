package com.kd8itx.plaguester.api.v1.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kd8itx.plaguester.domain.LatLong;
import com.kd8itx.plaguester.exception.LoginException;
import com.kd8itx.security.UserSession;

@RestController
public class GeoController {

	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/V1/Geo/Update", method = RequestMethod.POST)
    public Boolean LatLong(@RequestBody LatLong latLong, HttpServletRequest request) throws LoginException {
		UserSession.validate(request);
		
		log.debug(latLong.toString());
		
		return true;
    }
	
}

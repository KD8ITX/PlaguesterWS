package com.kd8itx.plaguester.api.v1.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kd8itx.plaguester.dao.RouteDAO;
import com.kd8itx.plaguester.domain.Route;
import com.kd8itx.plaguester.domain.external.LatLongExternal;
import com.kd8itx.plaguester.domain.external.RouteExternal;
import com.kd8itx.plaguester.exception.LoginException;
import com.kd8itx.plaguester.util.GeoUtil;
import com.kd8itx.security.UserSession;

@RestController
public class GeoController {

	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/V1/Geo/Update", method = RequestMethod.POST)
    public Boolean LatLong(@RequestBody LatLongExternal latLong, HttpServletRequest request) throws LoginException {
		UserSession session = UserSession.validate(request, true);
		
		log.debug(latLong.toString());
		GeoUtil.processLatLongToWorld(latLong.backendLatLong());
		
		return true;
    }
	
	@RequestMapping(value = "/V1/Geo/Route/Save", method = RequestMethod.POST)
    public Boolean RouteSave(@RequestBody RouteExternal route, HttpServletRequest request) throws LoginException {
		UserSession session = UserSession.validate(request, true);
		
		Route backendRoute = route.backendRoute(session.getPersonId());

		RouteDAO.createRoute(backendRoute);
		
		// Process route to see if Person discovered new part of the world
		GeoUtil.processRouteToWorld(backendRoute);
		
		return true;
    }
	
}

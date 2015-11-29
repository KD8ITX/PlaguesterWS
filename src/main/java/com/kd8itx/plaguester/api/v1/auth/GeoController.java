package com.kd8itx.plaguester.api.v1.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kd8itx.plaguester.dao.RouteDAO;
import com.kd8itx.plaguester.domain.LatLong;
import com.kd8itx.plaguester.domain.Route;
import com.kd8itx.plaguester.domain.external.LatLongExternal;
import com.kd8itx.plaguester.domain.external.RouteExternal;
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
	
	@RequestMapping(value = "/V1/Geo/Route/Save", method = RequestMethod.POST)
    public RouteExternal RouteSave(@RequestBody RouteExternal route, HttpServletRequest request) throws LoginException {
		UserSession.validate(request);
		
		//RouteDAO.createRoute(route);
		List<LatLongExternal> points = new ArrayList<LatLongExternal>();
		
		LatLongExternal p1 = new LatLongExternal();
		p1.setLat("lat1");
		p1.setLon("lon1");
		p1.setTime(new Date());
		points.add(p1);
		
		LatLongExternal p2 = new LatLongExternal();
		p2.setLat("lat2");
		p2.setLon("lon2");
		p2.setTime(new Date());
		points.add(p2);
		
		RouteExternal routee = new RouteExternal();
		routee.setEndTime(new Date());
		routee.setPersonId("abc");
		routee.setStartTime(new Date());
		routee.setPoints(points);
		
		
		
		return routee;
    }
	
}

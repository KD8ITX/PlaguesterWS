package com.kd8itx.plaguester.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;

import com.kd8itx.plaguester.domain.Route;

public class RouteDAO {
	private static BasicDAO<Route, ObjectId> routeDAO = MongoAccessor.createDAO(Route.class);
	
	public static ObjectId createRoute (Route route) {
		Key<Route> abc = routeDAO.save(route);
        
        return (ObjectId)abc.getId();
    }
}

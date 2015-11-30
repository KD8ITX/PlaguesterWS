package com.kd8itx.plaguester.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;

import com.kd8itx.plaguester.domain.WorldArea;

public class WorldAreaDAO {

	private static BasicDAO<WorldArea, ObjectId> worldAreaDAO = MongoAccessor.createDAO(WorldArea.class);
	
	public static WorldArea get(String worldAreaId) {
		WorldArea worldArea = worldAreaDAO.findOne("worldKey", worldAreaId);
		
		return worldArea;
	}
	
	public static ObjectId create (WorldArea worldArea) {
        Key<WorldArea> abc = worldAreaDAO.save(worldArea);
        
        return (ObjectId)abc.getId();
    }
}

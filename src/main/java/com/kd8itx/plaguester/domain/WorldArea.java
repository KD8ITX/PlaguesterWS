package com.kd8itx.plaguester.domain;

import java.util.Map;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

public class WorldArea {
	
	@Id
	// Format of this $Lat,$Long both should have 4 decimal points of precision
	private ObjectId id;
	
	@Indexed(unique=true, dropDups=true)
	private String worldKey;
	
	// Key = $Lat,$Long at 5 decimal points of precision, all possible values should be preloaded
	private Map<String,WorldPoint> grid;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Map<String, WorldPoint> getGrid() {
		return grid;
	}

	public void setGrid(Map<String, WorldPoint> grid) {
		this.grid = grid;
	}

	public String getWorldKey() {
		return worldKey;
	}

	public void setWorldKey(String worldKey) {
		this.worldKey = worldKey;
	}
}

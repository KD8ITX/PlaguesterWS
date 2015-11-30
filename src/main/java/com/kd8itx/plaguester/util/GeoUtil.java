package com.kd8itx.plaguester.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.bson.types.ObjectId;

import com.kd8itx.plaguester.dao.WorldAreaDAO;
import com.kd8itx.plaguester.domain.LatLong;
import com.kd8itx.plaguester.domain.Route;
import com.kd8itx.plaguester.domain.WorldArea;
import com.kd8itx.plaguester.domain.WorldPoint;

public class GeoUtil {
	
	public static void processLatLongToWorld(LatLong latLong) {
		// Move the Lat/Long from 5 decimal points to 4, remember that in the LatLong 
		// object the decimal point was already removed
		int lat = latLong.getLat() / 10;
		int lon = latLong.getLon() / 10;

		// Create the key for the ObjectId used to query Mongo
		String worldAreaId = lat +","+ lon;

		// Check if this part of the world exists yet
		WorldArea worldArea = WorldAreaDAO.get(worldAreaId);

		// If no WorldArea, time to create it!
		if (worldArea == null) {
			// Since we know this was null above, go ahead and initialize it
			worldArea = new WorldArea();
			worldArea.setWorldKey(worldAreaId);

			// This is not the same as above, this should bring us to an even 0 on the 5th decimal point
			// and give us our starting point to add in the points
			int startLat = lat * 10;
			int startLon = lon * 10;

			Map<String,WorldPoint> grid = new HashMap<String,WorldPoint>();
			for (int iLat = 0; iLat<10; iLat++) {
				for (int iLon = 0; iLon<10; iLon++) {
					int keyLat = startLat + iLat;
					int keyLon = startLon + iLon;

					String gridKey = keyLat +","+ keyLon;

					WorldPoint point = new WorldPoint();
					point.setTileId(ThreadLocalRandom.current().nextInt(1, 10 + 1));
					point.setLat(keyLat);
					point.setLon(keyLon);

					grid.put(gridKey, point);
				}
			}
			worldArea.setGrid(grid);

			// Save the WorldArea
			WorldAreaDAO.create(worldArea);

		}
	}
	
	public static void processRouteToWorld(Route route) {
		for (LatLong latLong : route.getPoints()) {
			processLatLongToWorld(latLong);
		}
		
	}
}

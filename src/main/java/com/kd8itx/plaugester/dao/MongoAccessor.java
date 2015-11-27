package com.kd8itx.plaugester.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class MongoAccessor {

	private static final String dbname = "Plaguester";// System.getProperty("dbname");
    private static final String host = "54.149.23.97:27017"; //System.getProperty("mongohost");
    private static final String username = ""; //System.getProperty("mongouser");
    private static final String password = ""; //System.getProperty("mongopass");
    
    private static MongoClient mongo = null;
    private static DatastoreImpl datastore = null;
    
    private static final Logger log = Logger.getLogger(MongoAccessor.class);
    
    static {
        if (host == null || dbname == null) {
            log.error("Unable to find one or more of the JVM environment variables \"mongohost\" & \"dbname\". Please set them.");
        } else {
            try {
                log.info("Connecting to Mongo...");
                
                // Loop through the list of mongo servers and then connect to them.
                // We expect that we will be connecting to each member of a replica set
                List<ServerAddress> mongoServers = new ArrayList<ServerAddress>();
                String[] mongoHosts = host.split(" ");
                for (String mongoHost : mongoHosts) {
                	String[] hostParts = mongoHost.split(":");
                	if (hostParts.length < 2) {
                		throw new Exception("Mongo host was not formatted correctly (ex: 192.168.1.42:27017).  Got ["+mongoHost+"]");
                	}
                	mongoServers.add(new ServerAddress(hostParts[0], Integer.parseInt(hostParts[1])));
                }
                
                MongoClientOptions options = MongoClientOptions.builder().writeConcern(WriteConcern.ACKNOWLEDGED)
                                                                         .connectionsPerHost(40)
                                                                         .threadsAllowedToBlockForConnectionMultiplier(100)
                                                                         .build();
                
                // Make sure this is backwards compatible with any systems that don't have a password set
                if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
                	MongoCredential credential = MongoCredential.createMongoCRCredential(username, dbname, password.toCharArray());
                	mongo = new MongoClient(mongoServers, Arrays.asList(credential), options);
                } else {
                	log.warn("No mongo username/password set in the JVM environmental variables. Will attempt to connect to mongo without username/password.");
                	mongo = new MongoClient(mongoServers, options);
                }
                
                datastore = new DatastoreImpl(new Morphia(), mongo, dbname);
                mongo.getDB(dbname).command("ping"); //This will throw an exception if there is no connection to mongo
                log.info("Connection established");
            } catch (Exception e) {
                log.error("Unable to connect to mongo.", e);
            }
        } 
    }
    
    public static <T> BasicDAO<T, ObjectId> createDAO(Class<T> clazz) {
        return new BasicDAO<T, ObjectId>(clazz, datastore);
    }
}

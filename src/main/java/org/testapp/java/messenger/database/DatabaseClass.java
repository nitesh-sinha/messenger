package org.testapp.java.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.testapp.java.messenger.model.Message;
import org.testapp.java.messenger.model.Profile;

// Stub for an actual database class(JDBC/Hibernate etc. to connect to a database)
public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
}

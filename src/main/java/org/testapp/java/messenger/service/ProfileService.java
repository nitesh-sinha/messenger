package org.testapp.java.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testapp.java.messenger.database.DatabaseClass;
import org.testapp.java.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("john", new Profile(1L, "john", "John", "Doe"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile p1) {
		p1.setId(profiles.size() + 1);
		profiles.put(p1.getProfileName(), p1);
		return p1;
	}
	
	public Profile updateProfile(Profile p1) {
		if (p1.getProfileName().isEmpty()) {
			return null;
		} else {
			profiles.put(p1.getProfileName(), p1);
			return p1;
		}
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}


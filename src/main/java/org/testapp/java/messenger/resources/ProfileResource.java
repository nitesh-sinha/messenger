package org.testapp.java.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.testapp.java.messenger.model.Profile;
import org.testapp.java.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	private ProfileService profSvc = new ProfileService();
	
	@GET
	public List<Profile> getProfiles() {
		return profSvc.getAllProfiles();
	}
	
	@POST
	public Profile addProfile(Profile p1) {
		return profSvc.addProfile(p1);
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profName) {
		return profSvc.getProfile(profName);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profName, Profile p1) {
		p1.setProfileName(profName);
		return profSvc.updateProfile(p1);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profName) {
		profSvc.removeProfile(profName);
	}
}


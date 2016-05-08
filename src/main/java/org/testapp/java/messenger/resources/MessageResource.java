package org.testapp.java.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.testapp.java.messenger.model.Message;
import org.testapp.java.messenger.resources.beans.MessageFilterBean;
import org.testapp.java.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
// Class level annotation: If client passes Accept: text/xml header in the request, 
// server will now be able to send both XML and JSON responses
// @Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML}) 
public class MessageResource {
	
	MessageService msgSvc = new MessageService();

	@GET
	// Eg: http://localhost:8080/messenger/webapi/messages?year=2016
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonMessages(@BeanParam MessageFilterBean filterBean) {
		System.out.println("JSON method called");
		// If query params not passed, their default value is 0
		if (filterBean.getYear() > 0) {
			return msgSvc.getAllMessagesForYear(filterBean.getYear());
		} 
		if (filterBean.getStart() >=0 && filterBean.getSize() >0) {
			return msgSvc.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return msgSvc.getAllMessages();
	}
	
	@GET
	// Eg: http://localhost:8080/messenger/webapi/messages?year=2016
	@Produces(MediaType.TEXT_XML)
	// Method level annotation override for TEXT_XML handling
	public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterBean) {
		System.out.println("XML method called");
		// If query params not passed, their default value is 0
		if (filterBean.getYear() > 0) {
			return msgSvc.getAllMessagesForYear(filterBean.getYear());
		} 
		if (filterBean.getStart() >=0 && filterBean.getSize() >0) {
			return msgSvc.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return msgSvc.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long msgId, @Context UriInfo uriInfo) {
		Message msg = msgSvc.getMessage(msgId);
		// Implementing HATEOAS
		msg.addLink(getUriForSelf(uriInfo, msg), "self");
		msg.addLink(getUriForProfile(uriInfo, msg), "profile");
		msg.addLink(getUriForComments(uriInfo, msg), "comments");
		return msg;
	}

	private String getUriForComments(UriInfo uriInfo, Message msg) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)  // To pull up the path annotation from MessageResource class
				.path(MessageResource.class, "getCommentResource")  // To pull up the Path annotation from the method getCommentResource() defined inside MessageResource class 
				.path(CommentResource.class)
				.resolveTemplate("messageId", msg.getId())  // Since path annotation of getCommentResource() has messageId which should be resolved to a value
				.build()
				.toString();
		return uri;
	}

	private String getUriForProfile(UriInfo uriInfo, Message msg) {
		String uri = uriInfo.getBaseUriBuilder()
							.path(ProfileResource.class)
							.path(msg.getAuthor())
							.build()
							.toString();
		return uri;
	}

	private String getUriForSelf(UriInfo uriInfo, Message msg) {
		String uri = uriInfo.getBaseUriBuilder()    // http://localhost:8080/messenger/webapi/
			   .path(MessageResource.class)         // 										 /messages
			   .path(Long.toString(msg.getId()))
			   .build()
			   .toString();
		return uri;
	}
	
	@POST
	// Using Response Builder to change status code to 201
	// instead of the default 200 OK
	public Response addMessage(Message msg, @Context UriInfo ui) throws URISyntaxException {
		Message newMsg = msgSvc.addMessage(msg);
//		return Response.status(Status.CREATED)
//				.entity(newMsg)
//				.build();
		String newId = String.valueOf(newMsg.getId());
		URI uri = ui.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
					   .entity(newMsg)
					   .build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long msgId, Message msg) {
		msg.setId(msgId);
		return msgSvc.updateMessage(msg);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long msgId) {
		msgSvc.removeMessage(msgId);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
}

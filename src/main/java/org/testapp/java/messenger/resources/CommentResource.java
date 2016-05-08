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

import org.testapp.java.messenger.model.Comment;
import org.testapp.java.messenger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService comSvc = new CommentService();

	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long msgId) {
		return comSvc.getAllComments(msgId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long msgId,
							  Comment comment) {
		return comSvc.addComment(msgId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long msgId,
								 @PathParam("commentId") long cId,
								 Comment comment) {
		comment.setId(cId);
		return comSvc.updateComment(msgId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long msgId,
			 				  @PathParam("commentId") long cId) {
		comSvc.removeComment(msgId, cId);
	}
	
	
	@GET
	@Path("/{commentId}")
	// Parent param resource can be accessed here
	public Comment getComment(@PathParam("commentId") long cId,
							  @PathParam("messageId") long mId) {
		return comSvc.getComment(mId, cId);
	}
}

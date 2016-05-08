package org.testapp.java.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.testapp.java.messenger.database.DatabaseClass;
import org.testapp.java.messenger.model.Comment;
import org.testapp.java.messenger.model.ErrorMessage;
import org.testapp.java.messenger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long msgId) {
		Map<Long, Comment> comments = messages.get(msgId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long msgId, long comId) {
		Message msg = messages.get(msgId);
		Response resp = Response.status(Status.NOT_FOUND)
				.entity(new ErrorMessage("Not Found", 404, "Oops! Error doc WIP! :-) "))
				.build();
		if (msg == null) {
			// WebApplicationException is implemented in Jersey already
			// so no ExceptionMapper needed
			throw new WebApplicationException(resp);
		}
		Comment comm = msg.getComments().get(comId);
		if (comm == null) {
			// NotFoundException is a subclass of WebApplicationException
			throw new NotFoundException(resp);
		}
		return comm;
	}
	
	public Comment addComment(long msgId, Comment com) {
		Map<Long, Comment> comments = messages.get(msgId).getComments();
		com.setId(comments.size() + 1);
		comments.put(com.getId(), com);
		return com;
	}
	
	public Comment updateComment(long msgId, Comment com) {
		Map<Long, Comment> comments = messages.get(msgId).getComments();
		if (com.getId() < 0) {
			return null;
		}
		comments.put(com.getId(), com);
		return com;
	}
	
	public Comment removeComment(long msgId, long comId) {
		Map<Long, Comment> comments = messages.get(msgId).getComments();
		return comments.remove(comId);
	}
	
	
}

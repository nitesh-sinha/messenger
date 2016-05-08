package org.testapp.java.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import org.testapp.java.messenger.model.ErrorMessage;

// @Provider(Disabling this class to implement Web Application Exception)
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
// Catch all exception mapper to avoid seeing Jetty's XML error page
	@Override
	public Response toResponse(Throwable excp) {
		ErrorMessage msg = new ErrorMessage(excp.getMessage(), 500, "Booo! No error doc defined for this!");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(msg)
				.build();
	}
}

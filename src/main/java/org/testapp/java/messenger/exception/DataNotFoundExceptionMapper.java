// Map exceptions to HTTP responses in JAX-RS
// using ExceptionMapper class. Every ExceptionMapper
// in JAX-RS should implement ExceptionMapper interface

// So, now when the MessageService throws DataNotFoundException
// to the MessageResource, since MessageReosurce does not catch
// that exception, it gets bubbled up to JAX-RS framework and it 
// looks for all classes with "Provider" annotation which has
// implemented and thus can catch "DataNotFoundException" and 
// finds this toResponse() method.

package org.testapp.java.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.testapp.java.messenger.model.ErrorMessage;

@Provider
// Provider registers this ExceptionMapper class in Jersey
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException excp) {
		ErrorMessage msg = new ErrorMessage(excp.getMessage(), 404, "Booo! No error doc defined for this!");
		return Response.status(Status.NOT_FOUND)
				.entity(msg)
				.build();
	}

	
}

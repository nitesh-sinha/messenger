package org.testapp.java.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemo {

	@GET
	@Path("annotations")
	// Eg: http://localhost:8080/messenger/webapi/injectdemo/annotations;param=test
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("fooHeader") String header,
											@CookieParam("JSESSIONID") String cookie){
		return "Matrix param: " + matrixParam + " Header Param: " + header + 
				"Cookie Param: " + cookie;
	}
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		return "URI: " + path + "\ncookies = " + cookies;
	}
}

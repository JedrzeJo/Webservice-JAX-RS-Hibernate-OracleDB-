package org.jedrzej.messager.messager.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParamUsingAnnotation(@MatrixParam("param") String matrixParam, 
			@HeaderParam("customHeaderParam") String headerParam,
			@CookieParam("cookieName") String cookieParam){
		return "Matrix param: " + matrixParam + 
				"\nHeader param: " + headerParam + 
				"\nCookie param: " + cookieParam;
	} 
		/*@MatrixParam pass parameter by ; instead of ?
		 @HeaderParam
		 @CookieParam
		 @FormParam (key - value param) */
}

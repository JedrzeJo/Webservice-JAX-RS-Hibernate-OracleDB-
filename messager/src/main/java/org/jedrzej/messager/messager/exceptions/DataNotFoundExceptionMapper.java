package org.jedrzej.messager.messager.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper; //ExceptionMapper comes with JAX RS
import javax.ws.rs.ext.Provider;

import org.jedrzej.messager.messager.model.ErrorMessage;

			//when resource doesnt catch exception then 
			//jaxrs looks for all classes with @Provider when exception appears.
@Provider	//@Provider registers - let's know jaxrs that this class is there.
public class DataNotFoundExceptionMapper 
implements ExceptionMapper<DataNotFoundException>{
	
	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, 
				"http://randomHelpResource.com");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}
	
}

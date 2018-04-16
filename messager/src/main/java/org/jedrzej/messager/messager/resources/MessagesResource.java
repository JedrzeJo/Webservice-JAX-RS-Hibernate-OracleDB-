package org.jedrzej.messager.messager.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jedrzej.messager.messager.model.Message;
import org.jedrzej.messager.messager.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessagesResource {

	MessageService messageservice= new MessageService();
	
	@GET //first api
	public List <Message> getMessages(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size) {
		if (year > 0 ) {
			return messageservice.getAllMessagesByYear(year);
		}
		if(start >= 0 && size >= 0 ) {
			return messageservice.getAllMessagesPaginated(start, size);
		}
		return messageservice.getAllMessages();	
	}
	
	@GET //second api
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		return messageservice.getMessage(id);
	}
	
	@POST
	public Message addMessage(Message message) {
		return messageservice.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageservice.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageservice.removeMessage(id);
	}
	
	
}

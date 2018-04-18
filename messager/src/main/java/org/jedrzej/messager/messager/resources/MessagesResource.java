package org.jedrzej.messager.messager.resources;

import java.net.URI;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jedrzej.messager.messager.model.Message;
import org.jedrzej.messager.messager.resources.beans.MessageFilterBean;
import org.jedrzej.messager.messager.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessagesResource {

	private MessageService messageservice= new MessageService();
	
	@GET //first api
	public List <Message> getMessages(@BeanParam MessageFilterBean mfBean) {
		if (mfBean.getYear() > 0 ) {
			return messageservice.getAllMessagesByYear(mfBean.getYear());
		}
		if(mfBean.getStart() >= 0 && mfBean.getSize() >= 0 ) {
			return messageservice.getAllMessagesPaginated(mfBean.getStart(),
					mfBean.getSize());
		}
		return messageservice.getAllMessages();	
	}
	
	@GET //second api
	@Path("/{messageId}") //links delivered in this single method (others to be done)
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message = messageservice.getMessage(id);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		//with comments it's gonna be a bit tricky
		message.addLink(getUriForComments(uriInfo, message), "comments");
		return message;
	}

	
	
	private String getUriForComments(UriInfo uriInfo, Message message) {
		URI uri =uriInfo.getBaseUriBuilder()
				.path(MessagesResource.class)
				.path(MessagesResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId()) 		
				//repclaces messageId with actual messageId in url
				.build();
		return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		URI uri =uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build();
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessagesResource.class)
				.path(Long.toString(message.getId()))
				.build()
				.toString();
		return uri;
	}
	
	
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageservice.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
						//created dodaje statusHTTP oraz header z nowym url.
						.entity(newMessage) //dodaje instancje do opowiedzi
						.build();
		/* przykladowe inne f. Response'a : .accepted() .notModified()
		 	.ok() - zwraca status 200	*/
		
		/*return messageservice.addMessage(message); 
		odpowiedz bez statusu, oraz bez linku w headerze do nowopowstalej instancji*/
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
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	
}

package org.jedrzej.messager.messager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jedrzej.messager.messager.database.DatabaseClass;
import org.jedrzej.messager.messager.model.Comment;
import org.jedrzej.messager.messager.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List <Comment> getAllComments (long messageId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
}

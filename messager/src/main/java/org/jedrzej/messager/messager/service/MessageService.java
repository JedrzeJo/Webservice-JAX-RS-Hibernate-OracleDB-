package org.jedrzej.messager.messager.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.jedrzej.messager.messager.database.DatabaseClass;
import org.jedrzej.messager.messager.exceptions.DataNotFoundException;
import org.jedrzej.messager.messager.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1L, "dupa", "Jedrzej"));
		messages.put(2L, new Message(2L, "pozdrawiam mariusza", "Cyc"));
	}
	
	public List <Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	
	
	public List <Message> getAllMessagesByYear (int year){
		List <Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreation_date());
			if(cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	
	
	public List <Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<>(messages.values());
		if(start + size > list.size()) {
			return new ArrayList <Message>();
		}
		return list.subList(start, start+size);
	}
	
	
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
		return message;
	}	
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId()<=0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}

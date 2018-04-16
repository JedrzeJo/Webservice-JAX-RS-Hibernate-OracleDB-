package org.jedrzej.messager.messager.model;

import java.util.Date;

public class Comment {
	
	private long id;
	private String message;
	private Date creation_date;
	private String author;
	
	public Comment() {
		
	}
	
	public Comment(Long id, String message, String author) {
		this.id=id;
		this.message=message;
		this.author=author;
		this.creation_date=new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}

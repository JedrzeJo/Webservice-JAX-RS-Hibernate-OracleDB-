package org.jedrzej.messager.messager.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement	//in case of converting to json - NECESSARY as well
public class ErrorMessage {
	
	
	private String errorMessage; 	
	private int errorCode;			//for my owns codes
	private String documentation;	/*link to my online documentation 
										for exaple: how to fix issue*/
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}	
	
	
	
}

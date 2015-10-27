package com.flowerseeker.controllers.forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class SendEmailForm {


	@NotNull
	@NotEmpty(message= "Must Write a proper message")
	private String message;
	

	private String from;
	
	@NotNull
	@NotEmpty(message= "Must specify recipient")
	private String to;
	
	@NotNull
	@NotEmpty(message= "Must specify subject")	
	private String subject;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
 

}

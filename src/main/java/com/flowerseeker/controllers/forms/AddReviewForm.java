package com.flowerseeker.controllers.forms;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AddReviewForm {

 	
	@NotNull
	@NotEmpty(message= "Must Write a proper description")
	private String comment;
	
	@NotNull
	@NotEmpty
	private String username;
	
	@NotEmpty(message= "Must choose a rank value")
	@NotNull
	private String rank;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	
}

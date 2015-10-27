package com.flowerseeker.controllers.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class AddDeliveryAreaForm {
	@NotNull
	@Pattern(regexp="\\d{5}", message="Invalid zipcode")
	private String zipcode;

	@NotNull
	@NotEmpty(message="City cannot be empty")
	private String city;
	
	@NotNull
	@NotEmpty(message="Must select a state")
	private String state;

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}

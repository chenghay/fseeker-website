package com.flowerseeker.controllers.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class AddHourForm {

	@NotNull
	@NotEmpty(message= "Must select a day")
	private String day;
	
	@NotNull
	@Pattern(regexp="[0-1]?[0-9]:[0-5][0-9]", message="'From' must be in the form xx:xx")
	private String from;
	
	private String fromhalf;
	
	@NotNull
	@Pattern(regexp="[0-1]?[0-9]:[0-5][0-9]", message="'To' must be in the form xx:xx")
	private String to;

	private String tohalf;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFromhalf() {
		return fromhalf;
	}

	public void setFromhalf(String fromhalf) {
		this.fromhalf = fromhalf;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTohalf() {
		return tohalf;
	}

	public void setTohalf(String tohalf) {
		this.tohalf = tohalf;
	}
}

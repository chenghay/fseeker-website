package com.flowerseeker.controllers.forms;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class AddOrderForm {

	@NotNull
	@NotEmpty(message="Need To provide address")
	private String address1;
	
	private String address2;
	
	@NotNull
	@NotEmpty(message="City cannot be empty")
	private String city;
	
	@NotNull
	@NotEmpty(message="You must select a state")
	private String usState;
	
	@NotNull
	@Pattern(regexp="\\d{5}", message="Invalid zipcode")
	private String zipCode;
	 
	private String instructions;
	
	private boolean pickUp;
	
	private String total;

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUsState() {
		return usState;
	}

	public void setUsState(String usState) {
		this.usState = usState;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public boolean isPickUp() {
		return pickUp;
	}

	public void setPickUp(boolean pickUp) {
		this.pickUp = pickUp;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	

}

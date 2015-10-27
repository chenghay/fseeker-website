package com.flowerseeker.controllers.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ModifyStoreForm {

	@NotNull
	private Long id;

	@Email(message="Not a valid email")
	@NotEmpty(message="Email cannot be empty")
	@NotNull
	private String storeEmail;
	
	@NotNull
	@Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="Must be of the form xxx-xxx-xxxx")
	private String phone;
	
	@NotNull
	@NotEmpty(message="Store name must not be empty")
	private String storename;
	
	@NotNull
	@NotEmpty(message="Store address cannot be empty")
	private String address1;
	
	private String address2;
	
	@NotNull
	@NotEmpty(message="Store city cannot be empty")
	private String city;
	
	@NotNull
	@NotEmpty(message="You must select a state")
	private String state;
	
	@NotNull
	@Pattern(regexp="\\d{5}", message="Invalid zipcode")
	private String zipcode;
	
	@Email(message="Not a valid email")
	@NotEmpty(message="Paypal email cannot be empty")
	@NotNull
	private String paypal;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storeName) {
		this.storename = storeName;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getStoreEmail() {
		return storeEmail;
	}

	public void setStoreEmail(String storeEmail) {
		this.storeEmail = storeEmail;
	}

	public String getPaypal() {
		return paypal;
	}

	public void setPaypal(String paypal) {
		this.paypal = paypal;
	}
}

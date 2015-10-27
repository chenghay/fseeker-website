package com.flowerseeker.controllers.forms;

import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class FloristRegistrationForm {
		
	@Email(message="Not a valid email")
	@NotEmpty(message="Email cannot be empty")
	@NotNull
	private String email;
	
	@Email(message="Not a valid email")
	@NotEmpty(message="Store email cannot be empty")
	@NotNull
	private String storeEmail;
	
	@NotNull
	@Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="Store phone must be of the form xxx-xxx-xxxx")
	private String phone;
	
	@NotNull
	@NotEmpty(message="Must provide first name")
	private String firstname;
	
	@NotNull
	@NotEmpty(message="Must provide last name")
	private String lastname;
	
	@NotNull
	@Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="Phone must be of the form xxx-xxx-xxxx")
	private String userphone;
	
	@NotNull
	@Pattern(regexp="\\S+", message="Username cannot be empty or have spaces")
	private String username;
	
	@NotNull
	@Pattern(regexp="\\S+", message="Password cannot be empty or have spaces")
	private String password;
	
	@NotNull
	@Pattern(regexp="\\S+", message="Password cannot have spaces")
	private String confirmPassword;
	
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
	
	private boolean passwordMatch;
	
	@Email(message="Not a valid email")
	@NotEmpty(message="Paypal email cannot be empty")
	@NotNull
	private String paypal;
	
	@NotNull
	@Pattern(regexp="\\d{5}", message="Invalid zipcode")
	private String zipcode;

	@AssertTrue(message="Your passwords don't match!")
	public boolean isPasswordMatch() {
		if (this.password == null)
			return false;
	    return this.password.equals(this.confirmPassword);
	}
	
	public void setPasswordMatch(boolean passwordMatch) {
		this.passwordMatch = passwordMatch;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String name) {
		this.firstname = name;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}

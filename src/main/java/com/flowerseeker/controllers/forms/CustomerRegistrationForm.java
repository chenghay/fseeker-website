package com.flowerseeker.controllers.forms;

 
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class CustomerRegistrationForm {

	@Email(message="Must be a valid email address e.g. jhonfoo@johnfoo.org")
	@NotEmpty(message="Email cannot be empty")
	private String customerEmail;
	
	@NotNull
	@NotEmpty(message="First name cannot be empty")
	private String customerFirstName;
	
	@NotNull
	@NotEmpty(message="Last name cannot be empty")
	private String customerLastName;
	
	@NotNull
	@NotEmpty(message="User name cannot be empty")
	private String customerUsername;
	
	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	@NotEmpty
	@Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="Phone number must be in the form ###-###-### ")
	private String customerPhonenumber;
	
	@NotNull
	@Pattern(regexp="\\S+", message="Password cannot contain any spaces")
	private String customerPassword;

	@NotNull
	@Pattern(regexp="\\S+", message="Password cannot contain any spaces")
	private String customerRepeatPassword;
	
	private boolean passwordMatch;
	
	@AssertTrue(message="Your passwords don't match!")
	public boolean isPasswordsMatch(){
		if (this.customerPassword==null || this.customerRepeatPassword==null ) {
			return false;
		} 
		return customerPassword.equals(customerRepeatPassword);
	}
	
	public boolean getPasswordMatch() {
		return passwordMatch;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerPhonenumber() {
		return customerPhonenumber;
	}

	public void setCustomerPhonenumber(String customerPhonenumber) {
		this.customerPhonenumber = customerPhonenumber;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	
	public String getCustomerRepeatPassword() {
		return customerRepeatPassword;
	}

	public void setCustomerRepeatPassword(String customerRepeatPassword) {
		this.customerRepeatPassword = customerRepeatPassword;
	}
	
}

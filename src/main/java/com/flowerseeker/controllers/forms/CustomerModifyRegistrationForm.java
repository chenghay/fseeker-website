package com.flowerseeker.controllers.forms;
	 

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class CustomerModifyRegistrationForm {

		@NotNull
		private String id;
	
		@NotEmpty(message="First name cannot be empty")
		@NotNull
		private String customerFirstName;
		
		@NotEmpty(message="Last name cannot be empty")
		@NotNull
		private String customerLastName;
		
 
 		@NotEmpty
		@Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="Phone number must be in the form ###-###-### ")
		private String customerPhonenumber;
		
 		@Email(message="Invalid email")
 		@NotEmpty(message="Email cannot be empty")
 		@NotNull
 		private String email;
 		 
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	
	}

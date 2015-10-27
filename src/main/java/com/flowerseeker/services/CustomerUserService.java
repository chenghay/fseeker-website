package com.flowerseeker.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerseeker.controllers.forms.CustomerModifyRegistrationForm;
import com.flowerseeker.controllers.forms.CustomerRegistrationForm;
import com.flowerseeker.controllers.forms.FloristRegistrationForm;
import com.flowerseeker.controllers.forms.ModifyStoreForm;
import com.flowerseeker.dao.UserRepository;
import com.flowerseeker.domain.Store;
import com.flowerseeker.domain.User;

@Service
public class CustomerUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private ShaPasswordEncoder passwordEnconder;
	
	@Transactional
	public void createNewCustomer(CustomerRegistrationForm crf) {
		User customer= new User();
		customer.setEmail(crf.getCustomerEmail());
		customer.setEnabled(true);
		customer.setHasStore(false);
		customer.setIsAdmin(false);
		customer.setJoined(new Date());
		customer.setPhone(crf.getCustomerPhonenumber());
		customer.setPassword(passwordEnconder.encodePassword(crf.getCustomerPassword(), crf.getCustomerUsername()));
		customer.setUsername(crf.getCustomerUsername());	
		customer.setFirstname(crf.getCustomerFirstName());
		customer.setLastname(crf.getCustomerLastName());
		userRepository.save(customer);
	}
	
	@Transactional(readOnly=true)
	public void populateModifyUserForm(CustomerModifyRegistrationForm msf, String id) {
		User customer= getCustomerByUsername(id);	
		msf.setCustomerFirstName(customer.getFirstname());
		msf.setCustomerLastName(customer.getLastname());
		msf.setCustomerPhonenumber(customer.getPhone());
		msf.setEmail(customer.getEmail());
		msf.setId(id);		 
	}
	
	public User getCustomerByUsername(String u) {
		return userRepository.findOne(u);
	}

	@Transactional
	public void modifyUser(CustomerModifyRegistrationForm crf) {
		User customer= userRepository.findOne(crf.getId());
		customer.setEmail(crf.getEmail());
		customer.setPhone(crf.getCustomerPhonenumber());
		customer.setFirstname(crf.getCustomerFirstName());
		customer.setLastname(crf.getCustomerLastName());
		userRepository.save(customer);
	}
	
}

package com.flowerseeker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flowerseeker.dao.UserRepository;
import com.flowerseeker.domain.User;

@Service
public class AdminService {
	@Autowired
	private UserRepository userRepository;
	
	public User getCustomerByUsername(String u) {
		return userRepository.findOne(u);
	}
	
	
}

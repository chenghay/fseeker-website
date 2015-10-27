package com.flowerseeker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flowerseeker.dao.UserRepository;
import com.flowerseeker.domain.User;

@Service
public class UserService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findOne(username);
		if (user != null) {
			UserDetails ud = new CustomUserDetails(user);
			return ud;
		}
		return null;
	}

}

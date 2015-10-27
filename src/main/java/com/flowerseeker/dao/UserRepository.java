package com.flowerseeker.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerseeker.domain.User;

public interface UserRepository extends JpaRepository<User, String>{


	public List<User> findByOrdersStoreId(Long id);
	public Page<User> findByOrdersStoreId(Long id, Pageable page);

}

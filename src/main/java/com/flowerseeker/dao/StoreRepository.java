package com.flowerseeker.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerseeker.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

	public Store findByFloristUsername(String username);
	public Page<Store> findAll(Pageable page);
	
}

package com.flowerseeker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerseeker.domain.Zipcode;

public interface ZipcodeRepository extends JpaRepository<Zipcode, Long> {

	public List<Zipcode> findByCityAndZip(String city, String zip);
	public List<Zipcode> findByStoresIdOrderByZipAsc(Long id);
}

package com.flowerseeker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerseeker.domain.Order;
import com.flowerseeker.domain.OrderEntry;

public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long>{

	 public OrderEntry findByOrderId(Long id);
	 
	  
}

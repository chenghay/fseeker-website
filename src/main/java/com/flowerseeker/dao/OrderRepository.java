package com.flowerseeker.dao;

import java.util.List;

import java.util.Set;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerseeker.domain.Order;
 

import com.flowerseeker.domain.Product;


public interface OrderRepository extends JpaRepository<Order, Long>{


	public List<Order>  findByStoreId(Long id);
	public Order  findById(Long id); 
	public List<Order> findByCustomerUsername(String userid);
	
	public List<Order> findByStoreIdOrderByDateDesc(Long id); 
	public List<Order> findByCustomerUsernameAndStoreIdOrderByDateDesc(String uid, Long sid);
	public List<Order> findByOrderEntriesProductIdOrderByDateDesc(Long pid);
	public List<Order> findByCustomerUsernameAndStoreId(String uid, Long sid);

	public Page<Order> findByOrderEntriesProductId(Long pid, Pageable page);
	public Page<Order> findByCustomerUsernameAndStoreId(String uid, Long sid, Pageable page);
	public Page<Order> findByStoreId(Long id, Pageable page);
	public Page<Order> findAll(Pageable page);
}

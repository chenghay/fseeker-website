package com.flowerseeker.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerseeker.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	public List<Review> findByProductIdOrderByDateDesc(Long id);
	public List<Review> findByProductStoreIdOrderByDateDesc(Long id);
	public List<Review> findByCustomerUsernameAndProductStoreIdOrderByDateDesc(String username, Long id);
	public List<Review> findByCustomerUsernameAndProductStoreId(String username, Long id);
	
	public Page<Review> findByProductId(Long id, Pageable page);
	public Page<Review> findByCustomerUsernameAndProductStoreId(String username, Long id, Pageable page);
	public Page<Review> findByProductStoreId(Long id, Pageable page);
	
	public List<Review> findByProductIdAndCustomerUsername(Long id, String username);
}

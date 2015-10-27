package com.flowerseeker.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.data.jpa.repository.Query;

import com.flowerseeker.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findByStoreIdOrderByNameAsc(Long id); 
	
	public List<Product> findByPriceGreaterThanAndPriceLessThan(Float minPrice, Float maxPrice);	
	public List<Product> findByPriceGreaterThan(Float minPrice);
	public List<Product> findByPriceLessThan(Float maxPrice);
	public List<Product> findByStoreDeliverstoZip(String zipCode);
	
	//@Query("select p from Product p, Review r where p.id = r.product_id having avg(r.rank) >= ?1")
	public List<Product> findByReviewsRankGreaterThan(Integer rank);

	public List<Product> findByOccasionsName(String occasion);
	
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.pickup = ?1 WHERE p.store.id = ?2")
	public void setAllPickupForStore(boolean pickup, Long storeId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.tracking = ?1 WHERE p.store.id = ?2")
	public void setAllTrackingForStore(boolean track, Long storeId);
	
	public Page<Product> findByStoreId(Long id, Pageable page);
	
	 
	public Page<Product>  findAll(Pageable page);
}

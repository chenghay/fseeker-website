package com.flowerseeker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerseeker.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	public List<Subscription> findByStoreIdOrderByExpirationDesc(Long id);
	public Subscription findByPaypal(String id);
}

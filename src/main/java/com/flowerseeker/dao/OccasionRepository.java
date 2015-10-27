package com.flowerseeker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerseeker.domain.Occasion;

public interface OccasionRepository extends JpaRepository<Occasion, String> {

}

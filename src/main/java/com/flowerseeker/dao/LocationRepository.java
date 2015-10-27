package com.flowerseeker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerseeker.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

}

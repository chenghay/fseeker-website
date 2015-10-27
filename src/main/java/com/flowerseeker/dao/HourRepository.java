package com.flowerseeker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerseeker.domain.Hour;

public interface HourRepository extends JpaRepository<Hour, Long> {

}

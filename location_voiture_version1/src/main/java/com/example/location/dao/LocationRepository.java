package com.example.location.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Location;
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	
}

package com.example.location.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Marque;
@Repository
public interface MarqueRepository extends JpaRepository<Marque, Long>{
	Marque findByBrand(String brand);
}

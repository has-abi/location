package com.example.location.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Agence;
@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long>{
	Agence findByName(String name);
	Agence findByAdress(String adress);
	
}

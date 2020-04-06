package com.example.location.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Voiture;
@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long>{
	Page<Voiture> findAll(Pageable pageable);
	List<Voiture> findByMarqueBrandContains(String brand);
	List<Voiture> findByAgenceAdressContains(String adress);
	List<Voiture> findByCategorieNameContains(String name);
	List<Voiture> findByColorContains(String color);
	
}

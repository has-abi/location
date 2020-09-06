package com.example.location.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Voiture;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long>, JpaSpecificationExecutor<Voiture> {
	Page<Voiture> findAll(Pageable pageable);

	List<Voiture> findByMarqueBrandContains(String brand);

	List<Voiture> findByAgenceAdressContains(String adress);

	List<Voiture> findByCategorieNameContains(String name);
	
	List<Voiture> findByColorContains(String color);
	
	@Query(value = "select * from voiture where libelle like %:creteria% or color like %:creteria%",nativeQuery = true,countQuery = "select count(*) from voiture")
	Page<Voiture> search(@Param("creteria") String creteria,Pageable pageable);
	
	@Query(value = "select distinct color from voiture",nativeQuery = true)
	List<String> findAllColors();
	
	
	
	

}

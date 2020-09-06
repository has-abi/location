package com.example.location.service.facade;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.location.bean.Voiture;

public interface VoitureService {
	int save(Voiture voiture);

	int modify(Voiture voiture);

	int remove(Voiture voiture);

	List<Voiture> findAllWithPagination(String action);

	List<Voiture> findAll();

	List<Voiture> findAllByMarqueBrand(String brand);

	List<Voiture> findAllByAgenceAddress(String name);

	List<Voiture> findAllByCategorieName(String name);

	List<Voiture> findAllByColor(String color);

	Voiture findById(Long id);

	Page<Voiture> searchForVoitures(String cretiriaa,int page,int size);
	
	List<String> findAllColors();
}

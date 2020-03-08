package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.Voiture;

public interface VoitureService {
	int save(Voiture voiture);
	int modify(Voiture voiture);
	int remove(Voiture voiture);
	List<Voiture> findAll();
}

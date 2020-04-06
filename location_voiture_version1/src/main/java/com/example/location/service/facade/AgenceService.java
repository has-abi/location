package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.Agence;

public interface AgenceService {
	int save(Agence agence);
	int modify(Agence agence);
	int remove(Agence agence);
	List<Agence> findAll();
	Agence findByName(String name);
}

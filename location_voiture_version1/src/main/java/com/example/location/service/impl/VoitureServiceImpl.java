package com.example.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.location.bean.Voiture;
import com.example.location.dao.VoitureRepository;
import com.example.location.service.facade.VoitureService;

@Service
public class VoitureServiceImpl implements VoitureService {
	
	@Autowired
	private VoitureRepository voitureRepository;
	@Override
	public int save(Voiture voiture) {
		voitureRepository.save(voiture);
		return 1;
	}

	@Override
	public int modify(Voiture voiture) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Voiture voiture) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Voiture> findAll() {
		return voitureRepository.findAll();
	}

}

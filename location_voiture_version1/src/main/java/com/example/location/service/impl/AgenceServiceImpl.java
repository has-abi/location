package com.example.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.location.bean.Agence;
import com.example.location.dao.AgenceRepository;
import com.example.location.service.facade.AgenceService;
@Service
public class AgenceServiceImpl implements AgenceService{
	@Autowired
	private AgenceRepository agenceRepository;

	@Override
	public int save(Agence agence) {
		Agence ag = findByName(agence.getName());
		if(ag != null) {
			return -1;
		}else {
			agenceRepository.save(agence);
			return 1;
		}
	}

	@Override
	public int modify(Agence agence) {
		Agence ag = agenceRepository.findById(agence.getId()).get();
		if(ag == null) {
			return -1;
		}else {
			agenceRepository.save(agence);
			return 1;
		}
	}

	@Override
	public int remove(Agence agence) {
		Agence ag = findByName(agence.getName());
		if(ag == null) {
			return -1;
		}else {
			agenceRepository.delete(ag);
			return 1;
		}
	}

	@Override
	public List<Agence> findAll() {
		return agenceRepository.findAll();
	}

	@Override
	public Agence findByName(String name) {
		return agenceRepository.findByName(name);
	}

}

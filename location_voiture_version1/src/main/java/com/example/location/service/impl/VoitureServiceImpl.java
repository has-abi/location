package com.example.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		Voiture foundedVoiture = voitureRepository.findById(voiture.getId()).get();
		if(foundedVoiture == null) {
			return -1;
		}else {
			voitureRepository.save(voiture);
			return 1;
		}
	}

	@Override
	public int remove(Voiture voiture) {
		Voiture foundedVoiture = voitureRepository.findById(voiture.getId()).get();
		if(foundedVoiture == null) {
			return -1;
		}else {
			voitureRepository.delete(voiture);
			return 1;
		}
	}

	@Override
	public List<Voiture> findAllWithPagination(String action) {
		Pageable pageable;
		pageable = PageRequest.of(0, 6);
		if(action.equals("first")) {
			return this.voitureRepository.findAll(pageable).getContent();
		}else if(action.equals("next")) {
			return this.voitureRepository.findAll(pageable.next()).getContent();
		}else if(action.equals("prev")) {
			return this.voitureRepository.findAll(pageable.previousOrFirst()).getContent();
		}else {
			return null;
		}

	}

	@Override
	public List<Voiture> findAll() {
		return this.voitureRepository.findAll();
	}

	@Override
	public List<Voiture> findAllByMarqueBrand(String brand) {
		return voitureRepository.findByMarqueBrandContains(brand);
	}

	@Override
	public List<Voiture> findAllByAgenceAddress(String adress) {
		return voitureRepository.findByAgenceAdressContains(adress);
	}

	@Override
	public List<Voiture> findAllByCategorieName(String name) {
		return voitureRepository.findByCategorieNameContains(name);
	}

	@Override
	public List<Voiture> findAllByColor(String color) {
		return voitureRepository.findByColorContains(color);
	}

	@Override
	public Voiture findById(Long id) {
		return voitureRepository.findById(id).get();
	}

	
}

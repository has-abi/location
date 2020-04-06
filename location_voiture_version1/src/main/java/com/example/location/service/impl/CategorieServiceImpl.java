package com.example.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.location.bean.Categorie;
import com.example.location.dao.CategorieRepository;
import com.example.location.service.facade.CategorieService;

@Service
public class CategorieServiceImpl implements CategorieService {
	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public int save(Categorie categorie) {
		Categorie c = findByName(categorie.getName());
		if( c != null) {
			return -1;
		}else {
			categorieRepository.save(categorie);
			return 1;
		}
	}

	@Override
	public int modify(Categorie categorie) {
		Categorie c = categorieRepository.findById(categorie.getId()).get();
		if( c == null) {
			return -1;
		}else {
			categorieRepository.save(categorie);
			return 1;
		}
	}

	@Override
	public int remove(Categorie categorie) {
		Categorie c = findByName(categorie.getName());
		if( c == null) {
			return -1;
		}else {
			categorieRepository.delete(c);
			return 1;
		}
	}

	@Override
	public List<Categorie> findAll() {
		
		return categorieRepository.findAll();
	}

	@Override
	public Categorie findByName(String name) {
	
		return categorieRepository.findByName(name);
	}

}

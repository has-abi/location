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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(Categorie categorie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Categorie categorie) {
		// TODO Auto-generated method stub
		return 0;
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

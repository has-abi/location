package com.example.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.location.bean.Marque;
import com.example.location.dao.MarqueRepository;
import com.example.location.service.facade.MarqueService;

@Service
public class MarqueServiceImpl implements MarqueService{

	@Autowired
	private MarqueRepository marqueRepository;
	@Override
	public int save(Marque marque) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(Marque marque) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Marque marque) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Marque> findAll() {
	
		return marqueRepository.findAll();
	}

	@Override
	public Marque findByBrand(String brand) {
	
		return marqueRepository.findByBrand(brand);
	}
	
}

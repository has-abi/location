package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.Marque;

public interface MarqueService {
	
	int save(Marque marque);
	int modify(Marque marque);
	int remove(Marque marque);
	List<Marque> findAll();
	Marque findByBrand(String brand);
}

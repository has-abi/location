package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.Categorie;

public interface CategorieService {
	int save(Categorie categorie);
	int modify(Categorie categorie);
	int remove(Categorie categorie);
	List<Categorie> findAll();
	Categorie findByName(String name);
}
 
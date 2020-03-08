package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.Facture;

public interface FactureService {
	
	int save(Facture facture);
	int modify(Facture facture);
	int remove(Facture facture);
	List<Facture> findAll();
}

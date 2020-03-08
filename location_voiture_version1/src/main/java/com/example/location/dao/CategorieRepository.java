package com.example.location.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Categorie;
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	Categorie findByName(String name);
}

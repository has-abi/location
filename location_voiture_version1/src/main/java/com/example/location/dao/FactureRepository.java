package com.example.location.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Facture;
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long>{

}

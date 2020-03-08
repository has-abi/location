package com.example.location.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Voiture;
@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long>{

}

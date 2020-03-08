package com.example.location.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
package com.example.location.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
	User findByNumberPhone(String numberPhone);

}
package com.example.location.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	Cart findByClientId(Long id);
}

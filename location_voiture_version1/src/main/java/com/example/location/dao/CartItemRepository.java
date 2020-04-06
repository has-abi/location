package com.example.location.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Cart;
import com.example.location.bean.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findAllByCart(Cart cart,Pageable pageable);
	int countByCart(Cart cart);
}

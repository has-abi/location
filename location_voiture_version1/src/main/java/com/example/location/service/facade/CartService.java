package com.example.location.service.facade;

import com.example.location.bean.Cart;
import com.example.location.bean.User;

public interface CartService {
	Cart findByUserId(Long id);
	int create(Cart cart);
	int deleteById(Long id);
	int deleteByUser(User user);
	
}

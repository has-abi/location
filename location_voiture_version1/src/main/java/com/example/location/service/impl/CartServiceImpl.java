package com.example.location.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.location.bean.Cart;
import com.example.location.bean.User;
import com.example.location.dao.CartRepository;
import com.example.location.service.facade.CartService;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepository;

	@Override
	public Cart findByUserId(Long id) {
		return cartRepository.findByClientId(id);
	}

	@Override
	public int create(Cart cart) {
		Cart foundedCart = findByUserId(cart.getClient().getId());
		if(foundedCart != null) {
			return 2;
		}else {
			cartRepository.save(cart);
			return 1;
		}
	}

	@Override
	public int deleteById(Long id) {
		Cart cart  = cartRepository.findById(id).get();
		if(cart == null) {
			return -1;
		}else {
			cartRepository.delete(cart);
			return 1;
		}
	}

	@Override
	public int deleteByUser(User user) {
		Cart cart = findByUserId(user.getId());
		if(cart == null ) {
			return -1;
		}else {
			cartRepository.delete(cart);
			return 1;
		}
	}

}

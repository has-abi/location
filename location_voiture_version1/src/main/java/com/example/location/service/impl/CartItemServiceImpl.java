package com.example.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.location.bean.Cart;
import com.example.location.bean.CartItem;
import com.example.location.bean.Voiture;
import com.example.location.dao.CartItemRepository;
import com.example.location.service.facade.CartItemService;
import com.example.location.service.facade.CartService;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private CartService cartService;
	

	@Override
	public List<CartItem> findAllByCart(Cart cart,String action) {
		Pageable pageable;
		pageable = PageRequest.of(0, 3);
		if(action.equals("first")) {
			return cartItemRepository.findAllByCart(cart,pageable.first());
		}else if(action.equals("next")){
			return cartItemRepository.findAllByCart(cart,pageable.next());
		}else if(action.equals("prev")) {
			return cartItemRepository.findAllByCart(cart,pageable.previousOrFirst());
		}else{
			return null;
		}
		
	}

	@Override
	public int save(CartItem cartItem) {
		Cart cart = cartService.findByUserId(cartItem.getCart().getClient().getId());
		if( cart == null) {
			cartService.create(cartItem.getCart());
		}
			cartItem.setCart(cart);
			cartItemRepository.save(cartItem);
			return 1;

	}

	@Override
	public int delete(CartItem cartItem) {
		CartItem ci = cartItemRepository.findById(cartItem.getId()).get();
		if(ci == null) {
			return -1;
		}else {
			cartItemRepository.delete(ci);
			return 1;
		}
	}

	@Override
	public int countByCart(Cart cart) {
		return cartItemRepository.countByCart(cart);
	}

}

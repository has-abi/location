package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.Cart;
import com.example.location.bean.CartItem;

public interface CartItemService {
	List<CartItem> findAllByCart(Cart cart,String action);
	int save(CartItem cartItem);
	int delete(CartItem cartItem); 
	int countByCart(Cart cart);
}

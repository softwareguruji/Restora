package com.example.htl.W1.service;

import com.example.htl.W1.model.CartItem;

public interface CartItemService {

	CartItem save(CartItem cartItem);
	CartItem getById(Long cartItemId);
	void deleteById(Long cartItemid);
}

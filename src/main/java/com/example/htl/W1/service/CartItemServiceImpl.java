package com.example.htl.W1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htl.W1.model.CartItem;
import com.example.htl.W1.repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	CartItemRepository cartItemRepository;

	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItem getById(Long cartItemId) {
		return cartItemRepository.findOne(cartItemId);
	}

	@Override
	public void deleteById(Long cartItemid) {
		cartItemRepository.delete(cartItemid);
	}
	
}

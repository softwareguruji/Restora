package com.example.htl.W1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htl.W1.model.OrderStatus;
import com.example.htl.W1.repository.OrderStatusRepository;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

	@Autowired
	OrderStatusRepository orderStatusRepository;

	@Override
	public OrderStatus getById(Long id) {
		return orderStatusRepository.findOne(id);
	}
	
	
}

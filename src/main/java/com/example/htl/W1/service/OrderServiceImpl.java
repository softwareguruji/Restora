package com.example.htl.W1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htl.W1.model.Order;
import com.example.htl.W1.model.OrderStatus;
import com.example.htl.W1.repository.OrderRepository;
import com.example.model.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	OrderStatusService orderStatusService;

	
	@Override
	public List<Order> findUnOrderedCartItemsByUser(User userObj) {
		OrderStatus unOrderderedOrder = orderStatusService.getById(1L);
		return orderRepository.findByUserAndOrderStatus(userObj, unOrderderedOrder);
	}

	@Override
	public Order saveNewOrder(Order orderObj) {
		OrderStatus unOrderderedOrder = orderStatusService.getById(1L); //DB already filled with this ID
		orderObj.setOrderStatus(unOrderderedOrder);
		return orderRepository.save(orderObj);
	}
	
	@Override
	public Order savePlacedOrder(Order orderObj) {
		OrderStatus unOrderderedOrder = orderStatusService.getById(2L); //DB already filled with this ID
		orderObj.setOrderStatus(unOrderderedOrder);
		return orderRepository.save(orderObj);
	}

	@Override
	public Order getById(Long orderId) {
		return orderRepository.findOne(orderId);
	}

	@Override
	public List<Order> findAllOrderedCartItemsByUser(User userObj) {
		OrderStatus unOrderderedOrder = orderStatusService.getById(1L);
		List<OrderStatus> orderStatusList = new ArrayList<>();
		orderStatusList.add(unOrderderedOrder);
		return orderRepository.findByUserAndOrderStatusIsNotIn(userObj, orderStatusList);
	}

	@Override
	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> findAllOrdersByOrderStatus(OrderStatus orderStatus) {
		return orderRepository.findByOrderStatus(orderStatus);
	}

	@Override
	public Order completeOrder(Order orderObj) {
		OrderStatus unOrderderedOrder = orderStatusService.getById(5L); //DB already filled with this ID
		orderObj.setOrderStatus(unOrderderedOrder);
		return orderRepository.save(orderObj);
	}
	
}

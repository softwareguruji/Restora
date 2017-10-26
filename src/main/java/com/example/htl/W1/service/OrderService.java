package com.example.htl.W1.service;

import java.util.List;

import com.example.htl.W1.model.Order;
import com.example.htl.W1.model.OrderStatus;
import com.example.model.User;

public interface OrderService {

	List<Order> findUnOrderedCartItemsByUser(User userObj);
	List<Order> findAllOrderedCartItemsByUser(User userObj);
	List<Order> findAllOrders();
	List<Order> findAllOrdersByOrderStatus(OrderStatus orderStatus);
	Order saveNewOrder(Order orderObj);
	Order getById(Long orderId);
	Order savePlacedOrder(Order orderObj);
	Order completeOrder(Order orderObj);
}

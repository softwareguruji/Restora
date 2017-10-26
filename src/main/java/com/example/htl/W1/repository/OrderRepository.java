package com.example.htl.W1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.htl.W1.model.Order;
import com.example.htl.W1.model.OrderStatus;
import com.example.model.User;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByUserAndOrderStatus(User user, OrderStatus orderStatus);
	List<Order> findByUserAndOrderStatusIsNotIn(User user, List<OrderStatus> orderStatus);
	List<Order> findByOrderStatus(OrderStatus orderstatus);
}

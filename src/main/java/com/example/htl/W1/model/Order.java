package com.example.htl.W1.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.model.User;

@Entity
@Table(name="user_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id", columnDefinition="int(11)")
	private long orderId;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@OneToOne
	@JoinColumn(name = "order_status")
	private OrderStatus orderStatus;

	@OneToOne
	@JoinColumn(name = "user_id", columnDefinition="int(11)")
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy="order")
	List<CartItem> cartItems;
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	
}

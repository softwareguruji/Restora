package com.example.htl.W1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_status")
public class OrderStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_status_id", columnDefinition="int(11)")
	private long orderStatusId;

	@Column(name = "status")
	private String status;

	public long getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(long orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

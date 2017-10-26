package com.example.htl.W1.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_item")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_item_id", columnDefinition="int(11)")
	private long cartItemId;
	
	@OneToOne
	@JoinColumn(name = "menu_ref", columnDefinition="int(11)")
	private Menu menuId;
	
	@Column(name = "quantity", columnDefinition="int(2) default '1'")
	private int quantity;
	
	@Column(name = "item_price", columnDefinition="Decimal(10,2) default '0.00'")
	private double price;

	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="ref_order_id", nullable=false, columnDefinition="int(11)")
	private Order order;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy="refCartItem")
	List<CustomMenuCartItemSelection> customCartItemSelections;
	
	public List<CustomMenuCartItemSelection> getCustomCartItemSelections() {
		return customCartItemSelections;
	}

	public void setCustomCartItemSelections(List<CustomMenuCartItemSelection> customCartItemSelections) {
		this.customCartItemSelections = customCartItemSelections;
	}

	public long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Menu getMenuId() {
		return menuId;
	}

	public void setMenuId(Menu menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}

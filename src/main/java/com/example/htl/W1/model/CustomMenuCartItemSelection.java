package com.example.htl.W1.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cust_menu_cart_item_selections")
public class CustomMenuCartItemSelection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cust_menu_cart_item_selection_id", columnDefinition="int(11)")
	private long customMenuCartItemSelectionID;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="ref_cart_item_id", nullable=false, columnDefinition="int(11)")
	private CartItem refCartItem;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="ref_custom_menu_item_option_id", nullable=false, columnDefinition="int(11)")
	private CustomMenuItemOptions refCustomMenuItemOption;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="ref_base_item_selection_id", nullable=false, columnDefinition="int(11)")
	private BaseItem refSelectionBaseItem;
	
	@Column(name = "item_price", columnDefinition="Decimal(10,2) default '0.00'")
	private double price;

	public long getCustomMenuCartItemSelectionID() {
		return customMenuCartItemSelectionID;
	}

	public void setCustomMenuCartItemSelectionID(long customMenuCartItemSelectionID) {
		this.customMenuCartItemSelectionID = customMenuCartItemSelectionID;
	}

	public CartItem getRefCartItem() {
		return refCartItem;
	}

	public void setRefCartItem(CartItem refCartItem) {
		this.refCartItem = refCartItem;
	}

	public CustomMenuItemOptions getRefCustomMenuItemOption() {
		return refCustomMenuItemOption;
	}

	public void setRefCustomMenuItemOption(CustomMenuItemOptions refCustomMenuItemOption) {
		this.refCustomMenuItemOption = refCustomMenuItemOption;
	}

	public BaseItem getRefSelectionBaseItem() {
		return refSelectionBaseItem;
	}

	public void setRefSelectionBaseItem(BaseItem refSelectionBaseItem) {
		this.refSelectionBaseItem = refSelectionBaseItem;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}

package com.example.htl.W1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="base_items")
public class BaseItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "base_item_id", columnDefinition="int(11)")
	private long baseItemId;
	
	@Column(name = "item_name")
	@NotEmpty(message = "*Please provide Item Name")
	private String itemName;
	
	@OneToOne
	@JoinColumn(name = "item_type_ref")
	private ItemType itemTypeObj;
	
	@Column(name = "item_price", columnDefinition="Decimal(10,2) default '0.00'")
	private double price;

	
	/*@ManyToMany(mappedBy="listOfAvailableOptions")
	private Set<CustomMenuItemOptions> customMenuItemOptions;
	*/
	
	public long getBaseItemId() {
		return baseItemId;
	}

	public void setBaseItemId(long baseItemId) {
		this.baseItemId = baseItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public ItemType getItemTypeObj() {
		return itemTypeObj;
	}

	public void setItemTypeObj(ItemType itemTypeObj) {
		this.itemTypeObj = itemTypeObj;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

/*	public Set<CustomMenuItemOptions> getCustomMenuItemOptions() {
		return customMenuItemOptions;
	}

	public void setCustomMenuItemOptions(Set<CustomMenuItemOptions> customMenuItemOptions) {
		this.customMenuItemOptions = customMenuItemOptions;
	}
*/	
	
	
}

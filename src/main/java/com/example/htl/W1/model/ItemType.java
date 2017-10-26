package com.example.htl.W1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="item_type")
public class ItemType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_type_id")
	private long itemTypeId;
	
	@Column(name = "item_type_name")
	@NotEmpty(message = "*Please provide Item Type Name")
	private String itemTypeName;

	
	public long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	
	
	
}

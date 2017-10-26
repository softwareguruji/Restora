package com.example.htl.W1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu_type")
public class MenuType {

	@Id
	@Column(name="menu_type_id", columnDefinition="int(11)")
	private long menuTypeId;
	
	@Column(name="menu_type_name")
	private String menuTypeName;

	public long getMenuTypeId() {
		return menuTypeId;
	}

	public void setMenuTypeId(long menuTypeId) {
		this.menuTypeId = menuTypeId;
	}

	public String getMenuTypeName() {
		return menuTypeName;
	}

	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}
	
}

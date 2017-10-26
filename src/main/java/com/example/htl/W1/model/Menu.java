package com.example.htl.W1.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="menu_creator")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="menu_id", columnDefinition="int(11)")
	private long menuId;
	
	@Column(name="menu_name", nullable=false)
	private String menuName;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ref_menu_type_id", nullable=false)
	private MenuType menuType;

	@OneToOne(fetch=FetchType.LAZY, optional=true, mappedBy="menuItemReference", cascade={CascadeType.ALL})
	private FixedMenuItems fixedMenuItemObj;

	@OneToOne(fetch=FetchType.LAZY, optional=true, mappedBy="menuItemReference", cascade={CascadeType.ALL})
	private CustomMenuItem customMenuItemObj;
	
	
	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	public FixedMenuItems getFixedMenuItemObj() {
		return fixedMenuItemObj;
	}

	public void setFixedMenuItemObj(FixedMenuItems fixedMenuItemObj) {
		this.fixedMenuItemObj = fixedMenuItemObj;
	}

	public CustomMenuItem getCustomMenuItemObj() {
		return customMenuItemObj;
	}

	public void setCustomMenuItemObj(CustomMenuItem customMenuItemObj) {
		this.customMenuItemObj = customMenuItemObj;
	}
	
	
	
}

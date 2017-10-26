package com.example.htl.W1.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="fixed_menu_items")
public class FixedMenuItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="fixed_menu_item_id", columnDefinition="int(11)")
	private long fixedMenuItemsId;
	
	@Column(name="fixed_menu_description", columnDefinition="varchar(1000)")
	private String fixedMenuDescription;
	
	@Column(name = "fixed_menu_item_price", columnDefinition="Decimal(10,2) default '0.00'")
	private double price;
	
	@OneToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="ref_menu_id", nullable=false, columnDefinition="int(11)", foreignKey=@ForeignKey(name="fk_tbl_fmi_menu_id"), referencedColumnName="menu_id")
	//@PrimaryKeyJoinColumn(name="ref_menu_id", referencedColumnName="menu_id")
	private Menu menuItemReference;

	public long getFixedMenuItemsId() {
		return fixedMenuItemsId;
	}

	public void setFixedMenuItemsId(long fixedMenuItemsId) {
		this.fixedMenuItemsId = fixedMenuItemsId;
	}

	public String getFixedMenuDescription() {
		return fixedMenuDescription;
	}

	public void setFixedMenuDescription(String fixedMenuDescription) {
		this.fixedMenuDescription = fixedMenuDescription;
	}

	public Menu getMenuItemReference() {
		return menuItemReference;
	}

	public void setMenuItemReference(Menu menuItemReference) {
		this.menuItemReference = menuItemReference;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

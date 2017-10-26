package com.example.htl.W1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="custom_menu_item")
public class CustomMenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="custom_menu_item_id", columnDefinition="int(11)")
	private long customMenuItemId;
	
	@Column(name="custom_description", columnDefinition="varchar(1000)")
	private String customizationDescription;
	
	@OneToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="ref_menu_id", nullable=false, columnDefinition="int(11)", foreignKey=@ForeignKey(name="fk_tbl_cmi_menu_id"), referencedColumnName="menu_id")
	private Menu menuItemReference;

	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy="customMenuItemObj")
	private List<CustomMenuItemOptions> menuItemQuestions;
	
	public void addMenuItemQuestions(CustomMenuItemOptions cmiO){
		if(menuItemQuestions == null){
			menuItemQuestions = new ArrayList<CustomMenuItemOptions>();
		}
		menuItemQuestions.add(cmiO);
		cmiO.setCustomMenuItemObj(this);
	}
	
	public long getCustomMenuItemId() {
		return customMenuItemId;
	}

	public void setCustomMenuItemId(long customMenuItemId) {
		this.customMenuItemId = customMenuItemId;
	}

	public String getCustomizationDescription() {
		return customizationDescription;
	}

	public void setCustomizationDescription(String customizationDescription) {
		this.customizationDescription = customizationDescription;
	}

	public Menu getMenuItemReference() {
		return menuItemReference;
	}

	public void setMenuItemReference(Menu menuItemReference) {
		this.menuItemReference = menuItemReference;
	}

	public List<CustomMenuItemOptions> getMenuItemQuestions() {
		return menuItemQuestions;
	}

	public void setMenuItemQuestions(List<CustomMenuItemOptions> menuItemQuestions) {
		this.menuItemQuestions = menuItemQuestions;
	}


}

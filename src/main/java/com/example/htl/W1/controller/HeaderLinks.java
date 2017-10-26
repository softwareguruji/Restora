package com.example.htl.W1.controller;

public enum HeaderLinks {
	
	FIXED_MENU_ITEM("FIXED_MENU_ITEM"), 
	CUSTOM_MENU_ITEM("CUSTOM_MENU_ITEM"), 
	MANAGE_ITEM_TYPE("MANAGE_ITEM_TYPE"), 
	MANAGE_ITEM("MANAGE_ITEM"), 
	ORDER_MGMT("ORDER_MGMT"),
	ADD_MENU("ADD_MENU"), 
	LIST_MENU("LIST_MENU");
	
	private String link;
	HeaderLinks(String link){
		this.link = link;
	}
	
	public String getText(){
		return this.link;
	}
}

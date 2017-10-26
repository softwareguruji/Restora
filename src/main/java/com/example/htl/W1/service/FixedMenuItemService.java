package com.example.htl.W1.service;

import java.util.List;

import com.example.htl.W1.model.FixedMenuItems;
import com.example.htl.W1.model.Menu;

public interface FixedMenuItemService {

	FixedMenuItems save(FixedMenuItems fixedMenuItem);
	FixedMenuItems getByMenuItem(Menu menuObj);
	void delete(FixedMenuItems fixedMenuItem);
	
	List<FixedMenuItems> getByAll();
	
}

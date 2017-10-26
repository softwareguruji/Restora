package com.example.htl.W1.service;

import java.util.List;

import com.example.htl.W1.model.Menu;
import com.example.htl.W1.model.MenuType;

public interface MenuService {

	public Menu save(Menu menuObj);
	public List<Menu> getByAll();
	public List<Menu> getByAll(MenuType menuType);
	public Menu getByPK(long menuId);
	
}

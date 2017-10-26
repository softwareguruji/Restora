package com.example.htl.W1.service;

import java.util.List;

import com.example.htl.W1.model.MenuType;

public interface MenuTypeService {
	List<MenuType> getByAll();
	MenuType getByPk(long menuTypeId);
}

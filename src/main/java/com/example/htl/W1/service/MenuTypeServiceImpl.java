package com.example.htl.W1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htl.W1.model.MenuType;
import com.example.htl.W1.repository.MenuTypeRepository;

@Service
public class MenuTypeServiceImpl implements MenuTypeService {

	@Autowired
	MenuTypeRepository menuTypeRepository;
	
	@Override
	public List<MenuType> getByAll() {
		return menuTypeRepository.findAll();
	}

	@Override
	public MenuType getByPk(long menuTypeId) {
		return menuTypeRepository.findByMenuTypeId(menuTypeId);
	}
}

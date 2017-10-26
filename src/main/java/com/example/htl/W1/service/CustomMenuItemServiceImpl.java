package com.example.htl.W1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htl.W1.model.CustomMenuItem;
import com.example.htl.W1.model.Menu;
import com.example.htl.W1.repository.CustomMenuItemRepository;

@Service
public class CustomMenuItemServiceImpl implements CustomMenuItemService {

	@Autowired
	CustomMenuItemRepository customMenuItemRepository;

	@Override
	public void delete(CustomMenuItem customMenuItemObj) {
		customMenuItemRepository.delete(customMenuItemObj);
	}

	@Override
	public CustomMenuItem save(CustomMenuItem customMenuItemObj) {
		return customMenuItemRepository.save(customMenuItemObj);
	}

	@Override
	public CustomMenuItem getByMenuItem(Menu menuObj) {
		if(menuObj != null && menuObj.getMenuId() != 0){
			List<CustomMenuItem> customMenuItems = customMenuItemRepository.findByMenuItemReference(menuObj);
			if(customMenuItems.size()>0){
				return customMenuItems.get(0);
			}
		}
		return null;
	}

	@Override
	public List<CustomMenuItem> getByAll() {
		return customMenuItemRepository.findAll();
	}

	@Override
	public CustomMenuItem getByCustomMenuItemId(Long customMenuItemId) {
		return customMenuItemRepository.getOne(customMenuItemId);
	}
}

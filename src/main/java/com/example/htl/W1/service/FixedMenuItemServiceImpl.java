package com.example.htl.W1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htl.W1.model.FixedMenuItems;
import com.example.htl.W1.model.Menu;
import com.example.htl.W1.repository.FixedMenuItemRepository;

@Service
public class FixedMenuItemServiceImpl implements FixedMenuItemService {

	@Autowired
	FixedMenuItemRepository fixedMenuItemRepository;

	@Override
	public FixedMenuItems save(FixedMenuItems fixedmenuItem) {
		return fixedMenuItemRepository.save(fixedmenuItem);
	}

	@Override
	public FixedMenuItems getByMenuItem(Menu menuObj) {
		
		if(menuObj != null && menuObj.getMenuId() != 0){
			List<FixedMenuItems> fixedMenuItems = fixedMenuItemRepository.findByMenuItemReference(menuObj);
			if(fixedMenuItems.size()>0){
				return fixedMenuItems.get(0);
			}
		}
		return null;
	}

	@Override
	public void delete(FixedMenuItems fixedMenuItem) {
		fixedMenuItemRepository.delete(fixedMenuItem);
	}

	@Override
	public List<FixedMenuItems> getByAll() {
		return fixedMenuItemRepository.findAll();
	}
	
}

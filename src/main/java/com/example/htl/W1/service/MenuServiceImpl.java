package com.example.htl.W1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htl.W1.model.Menu;
import com.example.htl.W1.model.MenuType;
import com.example.htl.W1.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

	/*@Autowired
	private FixedMenuItemService fixedMenuItemService;*/
	
	@Override
	public Menu save(Menu menuObj) {

		/*menuObj = menuRepository.save(menuObj);
		
		if(menuObj.getFixedMenuItemObj() != null){
			if(menuObj.getFixedMenuItemObj().getFixedMenuDescription() != null){
				fixedMenuItemService.save(menuObj.getFixedMenuItemObj()); 	 	
			}
		}*/
		
		return menuRepository.save(menuObj);
	}

	@Override
	public List<Menu> getByAll() {
		return menuRepository.findAll();
	}

	@Override
	public List<Menu> getByAll(MenuType menuType) {
		return menuRepository.findByMenuType(menuType);
	}

	@Override
	public Menu getByPK(long menuId) {
		return menuRepository.findOne(menuId);
	}
	
	
}

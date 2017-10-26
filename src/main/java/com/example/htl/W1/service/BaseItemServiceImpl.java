package com.example.htl.W1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htl.W1.model.BaseItem;
import com.example.htl.W1.model.ItemType;
import com.example.htl.W1.repository.BaseItemRepository;

@Service
public class BaseItemServiceImpl implements BaseItemService {

	@Autowired
	BaseItemRepository baseItemRepository;
	
	@Override
	public void saveBaseItem(BaseItem baseItemObj) {
		baseItemRepository.save(baseItemObj);
	}

	@Override
	public List<BaseItem> getByAll() {
		return baseItemRepository.findAll();
	}
	
	@Override
	public List<BaseItem> getByAll(ItemType itemTypeObj) {
		if(itemTypeObj != null){
			return baseItemRepository.findByItemTypeObj(itemTypeObj);
		}else{
			return baseItemRepository.findAll();
		}
	}

	@Override
	public void deleteBaseItem(BaseItem baseItemObj) {
		baseItemRepository.delete(baseItemObj);
	}

	@Override
	public BaseItem getById(long baseItemId) {
		return baseItemRepository.findOne(baseItemId);
	}

	@Override
	public BaseItem getByItemNameAndItemType(BaseItem baseItemObj) {
		
		BaseItem searchedBaseItemObj = baseItemRepository.findByItemNameAndItemTypeObj(baseItemObj.getItemName(), baseItemObj.getItemTypeObj());
		
		if(baseItemObj.getBaseItemId() != 0
				&& baseItemObj.getBaseItemId() == searchedBaseItemObj.getBaseItemId()){
			return null;
		}
		
		return searchedBaseItemObj;
	}

}

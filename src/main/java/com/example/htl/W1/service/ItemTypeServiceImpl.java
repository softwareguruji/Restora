package com.example.htl.W1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htl.W1.model.ItemType;
import com.example.htl.W1.repository.ItemTypeRepository;

@Service("itemType")
public class ItemTypeServiceImpl implements ItemTypeService {

	@Autowired
	ItemTypeRepository itemTypeRepository;
	
	@Override
	public void saveItemType(ItemType itemTypeObj) {
		itemTypeRepository.save(itemTypeObj);
	}

	@Override
	public List<ItemType> getByAll() {
		return itemTypeRepository.findAll();
	}

	@Override
	public void deleteItemType(ItemType itemTypeObj) {
		itemTypeRepository.delete(itemTypeObj);
	}

	@Override
	public ItemType getById(long itemId) {
		return itemTypeRepository.findOne(itemId);
	}

	@Override
	public ItemType getByItemType(String itemType) {
		return itemTypeRepository.getByItemTypeName(itemType);
	}

}

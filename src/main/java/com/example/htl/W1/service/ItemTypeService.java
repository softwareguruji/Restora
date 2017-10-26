package com.example.htl.W1.service;

import java.util.List;

import com.example.htl.W1.model.ItemType;

public interface ItemTypeService{

	void saveItemType(ItemType itemTypeObj);
	List<ItemType> getByAll();
	void deleteItemType(ItemType itemTypeObj);
	ItemType getById(long itemId);
	ItemType getByItemType(String itemType);
}

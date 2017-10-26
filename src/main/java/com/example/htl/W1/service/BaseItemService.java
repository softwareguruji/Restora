package com.example.htl.W1.service;

import java.util.List;

import com.example.htl.W1.model.BaseItem;
import com.example.htl.W1.model.ItemType;

public interface BaseItemService {
	void saveBaseItem(BaseItem baseItemObj);
	List<BaseItem> getByAll(ItemType itemTypeObj);
	List<BaseItem> getByAll();
	void deleteBaseItem(BaseItem baseItemObj);
	BaseItem getById(long baseItemId);
	BaseItem getByItemNameAndItemType(BaseItem baseItemObj);
}

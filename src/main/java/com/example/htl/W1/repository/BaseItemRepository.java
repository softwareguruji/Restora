package com.example.htl.W1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.htl.W1.model.BaseItem;
import com.example.htl.W1.model.ItemType;

public interface BaseItemRepository extends JpaRepository<BaseItem, Long> {
	List<BaseItem> findByItemTypeObj(ItemType itemType);
	BaseItem findByItemNameAndItemTypeObj(String itemName, ItemType itemTypeObj);
}

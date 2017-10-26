package com.example.htl.W1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.htl.W1.model.ItemType;

@Repository("itemTypeRepository")
public interface ItemTypeRepository extends JpaRepository<ItemType, Long>{
	ItemType getByItemTypeName(String itemTypeName);
}

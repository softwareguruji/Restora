package com.example.htl.W1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.htl.W1.model.CustomMenuItem;
import com.example.htl.W1.model.Menu;
import java.util.List;

@Repository("customMenuItemRepository")
public interface CustomMenuItemRepository extends JpaRepository<CustomMenuItem, Long> {
	List<CustomMenuItem> findByMenuItemReference(Menu menuitemreference);
}

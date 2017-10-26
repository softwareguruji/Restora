package com.example.htl.W1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.htl.W1.model.FixedMenuItems;
import com.example.htl.W1.model.Menu;
import java.util.List;

@Repository("fixedMenuItemRepository")
public interface FixedMenuItemRepository extends JpaRepository<FixedMenuItems, Long> {
	List<FixedMenuItems> findByMenuItemReference(Menu menuitemreference);
}

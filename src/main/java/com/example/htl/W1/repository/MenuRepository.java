package com.example.htl.W1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.htl.W1.model.Menu;
import com.example.htl.W1.model.MenuType;
import java.util.List;

@Repository("menuRepository")
public interface MenuRepository extends JpaRepository<Menu, Long>{

	List<Menu> findByMenuType(MenuType menutype);
}

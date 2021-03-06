package com.example.htl.W1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.htl.W1.model.CustomMenuItem;

@Repository("customMenuItemOptionsRepository")
public interface CustomMenuItemOptionsRepository extends JpaRepository<CustomMenuItem, Long> {

}

package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.MultiLanguageProduct;

@Repository("multiLangProductRepository")
public interface MultiLangProductRepository extends JpaRepository<MultiLanguageProduct, Long>{
	List<MultiLanguageProduct> findByProductName(String productname);
}

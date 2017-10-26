package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.MultiLanguageProduct;


public interface ProductService {
	public List<MultiLanguageProduct> findProductByProductName(String productName);
	public void saveProduct(MultiLanguageProduct product);
	public List<MultiLanguageProduct> getAllProducts();
	public Page<MultiLanguageProduct> listAllByPage(Pageable pageable);
	public MultiLanguageProduct getProductById(long productId);
	public void updateProduct(MultiLanguageProduct product);
	public void deleteProduct(long productId);
}

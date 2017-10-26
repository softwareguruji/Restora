package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.MultiLanguageProduct;
import com.example.repository.MultiLangProductRepository;

@Service("product")
public class ProductServiceImpl implements ProductService {

	@Autowired
	MultiLangProductRepository productRepository;
	
	@Override
	public List<MultiLanguageProduct> findProductByProductName(String productName) {
		return productRepository.findByProductName(productName);
	}

	@Override
	public void saveProduct(MultiLanguageProduct product) {
		productRepository.save(product);
	}

	@Override
	public List<MultiLanguageProduct> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Page<MultiLanguageProduct> listAllByPage(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public MultiLanguageProduct getProductById(long productId) {
		return productRepository.getOne(productId);
	}

	@Override
	public void updateProduct(MultiLanguageProduct product) {
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(long productId) {
		productRepository.delete(productId);
	}

}

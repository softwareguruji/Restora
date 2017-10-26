package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.MultiLanguageProduct;
import com.example.service.ProductService;

@RestController
public class ProductMultiLanguageRestController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/admin/listProductRest", method = RequestMethod.GET)
	public Page<MultiLanguageProduct> list(Pageable pageable){
		Page<MultiLanguageProduct> pageUser = productService.listAllByPage(pageable);
		return pageUser;
	}
}

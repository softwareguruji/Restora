package com.example.controller.W2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.MultiLanguageProduct;
import com.example.service.ProductService;

@RestController
public class ProductRestControllerW2 {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/admin/w2/listProductRest", method = RequestMethod.GET)
	public Page<MultiLanguageProduct> list(Pageable pageable){
		Page<MultiLanguageProduct> pageUser = productService.listAllByPage(pageable);
		return pageUser;
	}
}

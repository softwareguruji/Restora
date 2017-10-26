package com.example.controller.W2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.MultiLanguageProduct;

@Controller
public class ProductControllerW2 {

	@RequestMapping(value = "/admin/w2/listProduct", method = RequestMethod.GET)
	public ModelAndView listProducts(){
		ModelAndView modelAndView = new ModelAndView();
		MultiLanguageProduct multiLangProdObj = new MultiLanguageProduct();
		modelAndView.addObject("product", multiLangProdObj);
		modelAndView.setViewName("/admin/ProductListW2/product_list");
		return modelAndView;
	}
}

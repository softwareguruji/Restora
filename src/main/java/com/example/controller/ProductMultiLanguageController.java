package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.MultiLanguageProduct;
import com.example.model.MultiLanguageProductSegment;
import com.example.service.ProductService;
import com.example.service.SegmentService;

@Controller
public class ProductMultiLanguageController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SegmentService segmentService;
	
	//All Product Controllers
	
	@RequestMapping(value="/admin/product", method = RequestMethod.GET)
	public ModelAndView product(){
		ModelAndView modelAndView = new ModelAndView();
		MultiLanguageProduct multiLangProdObj = new MultiLanguageProduct();
		modelAndView.addObject("product", multiLangProdObj);
		modelAndView.setViewName("/admin/product_add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/product", method = RequestMethod.POST)
	public ModelAndView createNewProduct(@ModelAttribute("product") @Valid MultiLanguageProduct productObj, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		productObj.setActive(true);
		
		long productId = productObj.getProductId();
		boolean isUpdateCall = false;
		if(productId != 0){
			isUpdateCall = true;
		}
		
		System.out.println("Is Manifold ? "+productObj.isManifold());
		
		List<MultiLanguageProduct> productExists = productService.findProductByProductName(productObj.getProductName());
		if (productExists != null
				&& !productExists.isEmpty()) {
			
			if(isUpdateCall){
				if(productExists.size()>0){
					if(productExists.get(0).getProductId() != productObj.getProductId()){
						bindingResult
							.rejectValue("productName", "error.product",
									"There is already a product registered with the same product name");
					}
				}
			}else{
				bindingResult
					.rejectValue("productName", "error.product",
							"There is already a product registered with the same product name");
			}
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/admin/product_add");
		} else {
			productService.saveProduct(productObj);
			modelAndView.addObject("product", new MultiLanguageProduct());
			if(isUpdateCall){
				modelAndView.addObject("successMessage", "Product has been updated successfully");
			}else{
				modelAndView.addObject("successMessage", "Product has been saved successfully");
			}
			modelAndView.setViewName("/admin/product_add");				
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/viewUpdateProduct", method = RequestMethod.GET)
	public ModelAndView viewUpdateProduct(@ModelAttribute("product_id") String productId){
		ModelAndView modelAndView = new ModelAndView();
		MultiLanguageProduct multiLangProdObj = productService.getProductById(Long.parseLong(productId));
		modelAndView.addObject("product", multiLangProdObj);
		modelAndView.setViewName("/admin/product_add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/deleteProduct", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@ModelAttribute("product_id") String productId){
		ModelAndView modelAndView = new ModelAndView();
		productService.deleteProduct(Long.parseLong(productId));
		modelAndView.setViewName("/admin/productlist_rest");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/listProduct", method = RequestMethod.GET)
	public ModelAndView listProducts(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/productlist_rest");
		return modelAndView;
	}

	//All Product Segment Controllers
	
	@RequestMapping(value = "/admin/viewProductSegment", method = RequestMethod.GET)
	public ModelAndView addListViewSegmentsByProduct(@ModelAttribute("product_id") String productId){
		ModelAndView modelAndView = new ModelAndView();
		
		MultiLanguageProduct multiLangProdObj = productService.getProductById(Long.parseLong(productId));
		modelAndView.addObject("product", multiLangProdObj);

		System.out.println(multiLangProdObj.getProductId());

		MultiLanguageProductSegment segment = new MultiLanguageProductSegment();
		modelAndView.addObject("segment", segment);
		
		modelAndView.setViewName("/admin/product_segment_add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/updateProductSegment", method = RequestMethod.POST)
	public ModelAndView addSegmentsByProduct(@ModelAttribute("productId") String productId, @ModelAttribute("segment") @Valid MultiLanguageProductSegment segment){
		ModelAndView modelAndView = new ModelAndView();
		
		MultiLanguageProduct multiLangProdObj = productService.getProductById(Long.parseLong(productId));
		modelAndView.addObject("product", multiLangProdObj);

		//System.out.println(multiLangProdObj.getProductId());
		segment.setProduct(multiLangProdObj);
		segmentService.save(segment);
		modelAndView.addObject("segment", segment);
		
		modelAndView.setViewName("/admin/product_segment_add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/listProductSegments", method = RequestMethod.GET)
	public ModelAndView listProductSegments(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/productsegmentlist_rest");
		return modelAndView;
	}

}

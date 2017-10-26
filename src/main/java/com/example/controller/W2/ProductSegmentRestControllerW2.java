package com.example.controller.W2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.MultiLanguageProductSegment;
import com.example.service.SegmentService;

@RestController
public class ProductSegmentRestControllerW2 {
	@Autowired
	private SegmentService segmentService;
	
	@RequestMapping(value="/admin/w2/listProductSegmentsRest", method = RequestMethod.GET)
	public List<MultiLanguageProductSegment> list(@ModelAttribute("productId") String productId){
		return segmentService.listByProductByPage(Long.parseLong(productId));
	}
}

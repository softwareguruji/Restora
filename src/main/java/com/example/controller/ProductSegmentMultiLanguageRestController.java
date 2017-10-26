package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.MultiLanguageProductSegment;
import com.example.service.SegmentService;

@RestController
public class ProductSegmentMultiLanguageRestController {

	@Autowired
	private SegmentService segmentService;
	
	@RequestMapping(value="/admin/listProductSegmentsRest", method = RequestMethod.GET)
	public Page<MultiLanguageProductSegment> list(Pageable pageable){
		Page<MultiLanguageProductSegment> pageProductSegments = segmentService.listAllByPage(pageable);
		return pageProductSegments;
	}
}

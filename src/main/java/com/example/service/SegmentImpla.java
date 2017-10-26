package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.MultiLanguageProduct;
import com.example.model.MultiLanguageProductSegment;
import com.example.repository.MultiLangProductSegmentRepository;

@Service("segment")
public class SegmentImpla implements SegmentService {

	@Autowired
	MultiLangProductSegmentRepository productSegmentRepository;
	
	@Override
	public List<MultiLanguageProductSegment> getAllSegments() {
		return productSegmentRepository.findAll();
	}

	@Override
	public void save(MultiLanguageProductSegment segment) {
		productSegmentRepository.save(segment);
	}

	@Override
	public Page<MultiLanguageProductSegment> listAllByPage(Pageable pageable) {
		return productSegmentRepository.findAll(pageable);
	}

	@Override
	public List<MultiLanguageProductSegment> listByProductByPage(long productId) {
		MultiLanguageProduct product = new MultiLanguageProduct();
		product.setProductId(productId);
		return productSegmentRepository.findByProduct(product);
	}

}

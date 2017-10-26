package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.MultiLanguageProductSegment;

public interface SegmentService {

	List<MultiLanguageProductSegment> getAllSegments();
	void save(MultiLanguageProductSegment segment);
	public Page<MultiLanguageProductSegment> listAllByPage(Pageable pageable);
	public List<MultiLanguageProductSegment> listByProductByPage(long productId);
}

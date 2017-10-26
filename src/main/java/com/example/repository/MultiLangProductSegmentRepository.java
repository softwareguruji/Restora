package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.MultiLanguageProduct;
import com.example.model.MultiLanguageProductSegment;
import java.lang.String;
import java.util.List;

public interface MultiLangProductSegmentRepository extends JpaRepository<MultiLanguageProductSegment, Long> {
	List<MultiLanguageProductSegment> findBySegmentEnglishName(String segmentenglishname);
	List<MultiLanguageProductSegment> findByProduct(MultiLanguageProduct product);
}

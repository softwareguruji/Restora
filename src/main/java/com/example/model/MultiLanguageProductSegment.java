package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Multi_Lang_Product_Segment")
public class MultiLanguageProductSegment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SEGMENT_ID")
	private long segmentId;
	
	@NotEmpty(message = "Segment must have it's English name. Rest of all can be empty.")
	@Column(name = "SEG_EN_NAME")
	private String segmentEnglishName;

	@Column(name = "SEG_FR_NAME")
	private String segmentFrenchName;
	
	@Column(name = "SEG_DE_NAME")
	private String segmentGermanName;

	@Column(name = "ACTIVE_STATE")
	private boolean active;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "REF_PROD_ID")
	@JsonIgnore
	private MultiLanguageProduct product;

	public long getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(long segmentId) {
		this.segmentId = segmentId;
	}

	public String getSegmentEnglishName() {
		return segmentEnglishName;
	}

	public void setSegmentEnglishName(String segmentEnglishName) {
		this.segmentEnglishName = segmentEnglishName;
	}

	public String getSegmentFrenchName() {
		return segmentFrenchName;
	}

	public void setSegmentFrenchName(String segmentFrenchName) {
		this.segmentFrenchName = segmentFrenchName;
	}

	public String getSegmentGermanName() {
		return segmentGermanName;
	}

	public void setSegmentGermanName(String segmentGermanName) {
		this.segmentGermanName = segmentGermanName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public MultiLanguageProduct getProduct() {
		return product;
	}

	public void setProduct(MultiLanguageProduct product) {
		this.product = product;
	}
	
	
}

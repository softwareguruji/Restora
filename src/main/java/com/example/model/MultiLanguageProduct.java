package com.example.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Multi_Lang_Product")
public class MultiLanguageProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROD_ID")
	private long productId;
	
	@NotEmpty(message = "Product Category must not be empty")
	@Column(name = "PROD_CATEGORY")
	private String productCategory;
	
	@NotEmpty(message = "Product Name must not be empty")
	@Column(name = "PROD_NAME")
	private String productName;
	
	@Column(name = "ACTIVE_STATE")
	private boolean active;
	
	@Column(name = "IS_MANIFOLD")
	private boolean manifold;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = "REF_PROD_ID")
	private Set<MultiLanguageProductSegment> productSegments;
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isManifold() {
		return manifold;
	}

	public void setManifold(boolean manifold) {
		this.manifold = manifold;
	}

	public Set<MultiLanguageProductSegment> getProductSegments() {
		return productSegments;
	}

	public void setProductSegments(Set<MultiLanguageProductSegment> productSegments) {
		this.productSegments = productSegments;
	}
	
}

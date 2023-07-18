package com.aakhya.smartcall.application.product.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class ProductPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8563038941695038159L;
	private Long productId;
	private Long companyId;
	public ProductPk() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductPk(Long productId, Long companyId) {
		super();
		this.productId = productId;
		this.companyId = companyId;
	}
//	@Id
//	@Column(name = "productid")
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
//	@Id
//	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}

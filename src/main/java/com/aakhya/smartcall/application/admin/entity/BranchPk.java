package com.aakhya.smartcall.application.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class BranchPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8569786263480909370L;
	private String branchCode;
	private Long companyId;
	public BranchPk() {
		super();
	}
	
	public BranchPk(String branchCode, Long companyId) {
		super();
		this.branchCode = branchCode;
		this.companyId = companyId;
	}
	@Id
	@Column(name="branchcode")
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	@Id
	@Column(name="companyid")
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	@Override
	public boolean equals(Object other) {
		if(other instanceof BranchPk) {
			BranchPk otherPk = (BranchPk)other;
			try {
				final boolean areEqual = branchCode.equals(otherPk.branchCode)
						&& companyId.equals(otherPk.companyId);
				return areEqual;
			} catch (Exception e) {
				if(System.identityHashCode(this) == System.identityHashCode(other))
					return true;
				else
					return false;
			}
		}else
			return false;
	}
}

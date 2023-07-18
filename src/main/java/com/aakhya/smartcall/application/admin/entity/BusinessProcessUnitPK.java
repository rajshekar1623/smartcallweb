package com.aakhya.smartcall.application.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class BusinessProcessUnitPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2002L;
	private Long businessUnitId;
	private Long processingUnitid;
	private Long companyId;

	public BusinessProcessUnitPK() {
		super();
	}

	public BusinessProcessUnitPK(Long businessUnitId, Long processingUnitid,
			Long companyId) {
		super();
		this.businessUnitId = businessUnitId;
		this.processingUnitid = processingUnitid;
		this.companyId = companyId;
	}

	public boolean equals(Object other) {
		if (other instanceof BusinessProcessUnitPK) {
			final BusinessProcessUnitPK otherPK = (BusinessProcessUnitPK) other;
			try {
				final boolean areEqual = (businessUnitId
						.equals(otherPK.businessUnitId)
						&& processingUnitid.equals(otherPK.processingUnitid) && companyId
						.equals(otherPK.companyId));
				return areEqual;
			} catch (Exception e) {
				if (System.identityHashCode(this) == System
						.identityHashCode(other))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public int hashCode() {
		if (null != businessUnitId && null != processingUnitid
				&& null != companyId)
			return businessUnitId.hashCode() + processingUnitid.hashCode()
					+ companyId.hashCode();
		return 2002;
	}

	@Id
	@Column(name = "businessunitid")
	public Long getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(Long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	@Id
	@Column(name = "processingunitid")
	public Long getProcessingUnitid() {
		return processingUnitid;
	}

	public void setProcessingUnitid(Long processingUnitid) {
		this.processingUnitid = processingUnitid;
	}

	@Id
	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}

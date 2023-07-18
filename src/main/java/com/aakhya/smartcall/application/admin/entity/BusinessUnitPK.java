package com.aakhya.smartcall.application.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class BusinessUnitPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2001L;
	private Long businessUnitId;
	private Long companyId;

	public BusinessUnitPK() {
		super();
	}

	public BusinessUnitPK(Long businessUnitId, Long companyId) {
		super();
		this.businessUnitId = businessUnitId;
		this.companyId = companyId;
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
	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public boolean equals(Object other) {
		if (other instanceof BusinessUnitPK) {
			BusinessUnitPK otherPK = (BusinessUnitPK) other;
			try {
				final boolean areEqual = businessUnitId
						.equals(otherPK.businessUnitId)
						&& companyId.equals(otherPK.companyId);
				return areEqual;
			} catch (Exception e) {
				if (System.identityHashCode(this) == System
						.identityHashCode(other))
					return true;
				return false;
			}
		}
		return false;
	}

	public int hashCode() {
		if (null != businessUnitId && null != companyId)
			return businessUnitId.hashCode() + companyId.hashCode();
		return 2001;
	}
}

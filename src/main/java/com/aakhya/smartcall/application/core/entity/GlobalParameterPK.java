package com.aakhya.smartcall.application.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class GlobalParameterPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5592733241424068255L;
	private String parameterName;
	private Long companyId;

	public GlobalParameterPK() {
		super();
	}

	public GlobalParameterPK(String parameterName, Long companyId) {
		this.parameterName = parameterName;
		this.companyId = companyId;
	}

	public boolean equals(Object other) {
		if (other instanceof GlobalParameterPK) {
			GlobalParameterPK otherPk = (GlobalParameterPK) other;
			boolean areEqual = (otherPk.parameterName.equals(parameterName) && otherPk.companyId
					.equals(companyId));
			return areEqual;
		}
		return false;
	}

	@Id
	@Column(name = "parametername")
	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
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

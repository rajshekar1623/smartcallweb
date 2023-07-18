package com.aakhya.smartcall.application.integration.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class IntegrationFileFormatDetailPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8849862577854499580L;
	private Long integrationId;
	private Long companyId;
	private Integer columnSequence;
	
	public IntegrationFileFormatDetailPk() {
		super();
	}

	public IntegrationFileFormatDetailPk(Long integrationId, Long companyId, Integer columnSequence) {
		super();
		this.integrationId = integrationId;
		this.companyId = companyId;
		this.columnSequence = columnSequence;
	}

	@Id
	@Column(name = "integrationid")
	public Long getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(Long integrationId) {
		this.integrationId = integrationId;
	}

	@Id
	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Id
	@Column(name = "columnsequence")
	public Integer getColumnSequence() {
		return columnSequence;
	}

	public void setColumnSequence(Integer columnSequence) {
		this.columnSequence = columnSequence;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof IntegrationFileFormatDetailPk) {
			IntegrationFileFormatDetailPk otherPk = (IntegrationFileFormatDetailPk)other;
			try {
				final boolean areEqual = integrationId.equals(otherPk.integrationId)
						&& companyId.equals(otherPk.companyId) && columnSequence.equals(otherPk.columnSequence);
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

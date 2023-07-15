package com.aakhya.smartcall.application.integration.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class IntegrationMasterPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6262822525611306925L;
	private Long integrationId;
	private Long companyId;
	
	public IntegrationMasterPk() {
		super();
	}

	public IntegrationMasterPk(Long integrationId, Long companyId) {
		super();
		this.integrationId = integrationId;
		this.companyId = companyId;
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
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof IntegrationMasterPk) {
			IntegrationMasterPk otherPk = (IntegrationMasterPk)other;
			try {
				final boolean areEqual = integrationId.equals(otherPk.integrationId)
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

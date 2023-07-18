package com.aakhya.smartcall.application.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class GenericClassifierPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5775299025518516355L;
	private Long genericId;
	private Long companyId;
	public GenericClassifierPk() {
		super();
	}
	public GenericClassifierPk(Long genericId, Long companyId) {
		super();
		this.genericId = genericId;
		this.companyId = companyId;
	}
	@Id
	@Column(name = "genericid")
	public Long getGenericId() {
		return genericId;
	}
	public void setGenericId(Long genericId) {
		this.genericId = genericId;
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
		if(other instanceof GenericClassifierPk) {
			GenericClassifierPk otherPk = (GenericClassifierPk)other;
			try {
				final boolean areEqual = genericId.equals(otherPk.genericId)
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

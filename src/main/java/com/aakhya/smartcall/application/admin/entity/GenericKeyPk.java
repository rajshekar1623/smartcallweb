package com.aakhya.smartcall.application.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class GenericKeyPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6612401071304911987L;
	private String genericKey;
	private Long companyId;
	public GenericKeyPk() {
		super();
	}
	public GenericKeyPk(String genericKey, Long companyId) {
		super();
		this.genericKey = genericKey;
		this.companyId = companyId;
	}
	@Id
	@Column(name="generickey")
	public String getGenericKey() {
		return genericKey;
	}
	public void setGenericKey(String genericKey) {
		this.genericKey = genericKey;
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
		if(other instanceof GenericKeyPk) {
			GenericKeyPk otherPk = (GenericKeyPk)other;
			try {
				final boolean areEqual = genericKey.equals(otherPk.genericKey)
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

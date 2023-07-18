package com.aakhya.smartcall.application.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class SequencePk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8957119118852699795L;
	private String entityName;
	private Long companyId;
	@Id
	@Column(name="entityname")
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	@Id
	@Column(name="companyid")
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public SequencePk() {
		super();
	}
	public SequencePk(String entityName, Long companyId) {
		super();
		this.entityName = entityName;
		this.companyId = companyId;
	}
	@Override
	public boolean equals(Object object) {
		if(object instanceof SequencePk) {
			SequencePk otherPk = (SequencePk)object;
			try {
				final boolean areEqual = entityName.equals(otherPk.entityName)
						&& companyId.equals(otherPk.companyId);
				return areEqual;
			} catch (Exception e) {
				if(System.identityHashCode(this) == System.identityHashCode(otherPk))
					return true;
				else 
					return false;
			}
		}
		return false;
	}
}

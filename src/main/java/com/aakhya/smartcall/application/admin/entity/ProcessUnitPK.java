package com.aakhya.smartcall.application.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class ProcessUnitPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2006L;
	private Long processingUnitid;
	private Long companyId;
	public ProcessUnitPK() {
		super();
	}
	public ProcessUnitPK(Long processingUnitid, Long companyId) {
		super();
		this.processingUnitid = processingUnitid;
		this.companyId = companyId;
	}
	@Id
	@Column(name="processingunitid")
	public Long getProcessingUnitid() {
		return processingUnitid;
	}
	public void setProcessingUnitid(Long processingUnitid) {
		this.processingUnitid = processingUnitid;
	}
	@Id
	@Column(name="companyid")
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public boolean equals(Object other){
		if(other instanceof ProcessUnitPK){
			ProcessUnitPK otherPK = (ProcessUnitPK)other;
			try{
				final boolean areEqual = processingUnitid.equals(otherPK.processingUnitid)
						&& companyId.equals(otherPK.companyId);
				return areEqual;
			}catch(Exception e){
				if (System.identityHashCode(other) == System
						.identityHashCode(this))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	public int hashCode() {
		if (null != processingUnitid && null != companyId) {
			return processingUnitid.hashCode()+companyId.hashCode();
		}
		return 2006;
	}
}

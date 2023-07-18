package com.aakhya.smartcall.application.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "sc_sequence")
@IdClass(SequencePk.class)
public class Sequence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4589758211298275748L;
	private String entityName;
	private Long companyId;
	private String branchCode;
	private Long currentSequence;
	private Date validFrom;
	private Date validTo;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private Date removeDatetime;
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
	@Column(name="branchcode")
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	@Column(name="currentsequence")
	public Long getCurrentSequence() {
		return currentSequence;
	}
	public void setCurrentSequence(Long currentSequence) {
		this.currentSequence = currentSequence;
	}
	@Column(name="validfrom")
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	@Column(name="validto")
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	@Column(name="createdby")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="createddatetime")
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	@Column(name="updatedby")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="updateddatetime")
	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	@Column(name="removedatetime")
	public Date getRemoveDatetime() {
		return removeDatetime;
	}
	public void setRemoveDatetime(Date removeDatetime) {
		this.removeDatetime = removeDatetime;
	}
	
}

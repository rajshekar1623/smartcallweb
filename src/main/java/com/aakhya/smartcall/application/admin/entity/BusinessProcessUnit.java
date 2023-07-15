package com.aakhya.smartcall.application.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "ga_businessprocessmap")
@IdClass(BusinessProcessUnitPK.class)
@NamedQuery(name = "BusinessProcessUnit.findBusinessProcessUnits", query = "select distinct o from BusinessProcessUnit o where o.businessUnitId = :businessUnitId and o.status = :status and o.companyId = :companyId")
public class BusinessProcessUnit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private Long businessUnitId;
	private Long processingUnitid;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private Date removeDateTime;
	private Date effectiveStartDate;
	private Date effectiveEndDate;
	private BusinessUnit businessUnit;
	private ProcessUnit processUnit;
	private Long companyId;
	private String status;
	private boolean delete;

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

	@Column(name = "createdby")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "createddatetime")
	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Column(name = "updatedby")
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updateddatetime")
	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	@Column(name = "removedatetime")
	public Date getRemoveDateTime() {
		return this.removeDateTime;
	}

	public void setRemoveDateTime(Date removeDateTime) {
		this.removeDateTime = removeDateTime;
	}

	@Column(name = "effectivestartdate")
	public Date getEffectiveStartDate() {
		return this.effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	@Column(name = "effectiveenddate")
	public Date getEffectiveEndDate() {
		return this.effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "businessunitid", referencedColumnName = "businessunitid", insertable = false, updatable = false),
			@JoinColumn(name = "companyid", referencedColumnName = "companyid", insertable = false, updatable = false) })
	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "processingunitid", referencedColumnName = "processingunitid", insertable = false, updatable = false),
			@JoinColumn(name = "companyid", referencedColumnName = "companyid", insertable = false, updatable = false) })
	public ProcessUnit getProcessUnit() {
		return processUnit;
	}

	public void setProcessUnit(ProcessUnit processUnit) {
		this.processUnit = processUnit;
	}

	

	@Column(name = "companyid")
	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Transient
	public boolean isDelete() {
		return delete;
	}

	
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}

	public boolean equals(Object other) {
		if (other instanceof BusinessProcessUnit) {
			final BusinessProcessUnit otherBusinessProcessUnit = (BusinessProcessUnit) other;
			try {
				final boolean areEqual = (businessUnitId
						.equals(otherBusinessProcessUnit.businessUnitId)
						&& processingUnitid
								.equals(otherBusinessProcessUnit.processingUnitid) && companyId
						.equals(otherBusinessProcessUnit.companyId));
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
				&& null != companyId) {
			return businessUnitId.hashCode() + processingUnitid.hashCode()
					+ companyId.hashCode();
		}
		return 2;
	}
}

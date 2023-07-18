package com.aakhya.smartcall.application.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "GA_GLOBALPARAMETER_NEW")
@IdClass(GlobalParameterPK.class)
public class GlobalParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	private String parameterName;
	private Long companyId;
	private String parameterType;
	private String stringValue;
	private Date dateValue;
	private Long numericValue;
	private Double decimalValue;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private Date removeDateTime;
	private Date effectiveStartDate;
	private Date effectiveEndDate;
	private String status;
	

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

	@Column(name = "parametertype")
	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	@Column(name = "stringvalue")
	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	@Column(name = "datevalue")
	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	@Column(name = "numericvalue")
	public Long getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(Long numericValue) {
		this.numericValue = numericValue;
	}

	@Column(name = "decimalvalue")
	public Double getDecimalValue() {
		return decimalValue;
	}

	public void setDecimalValue(Double decimalValue) {
		this.decimalValue = decimalValue;
	}

	public boolean equals(Object other) {
		if (other instanceof GlobalParameter) {
			GlobalParameter otherGlobalParameter = (GlobalParameter) other;
			final boolean areEqual = parameterName
					.equals(otherGlobalParameter.parameterName)
					&& companyId.equals(otherGlobalParameter.companyId);
			return areEqual;
		}
		return false;
	}

	public int hashCode() {
		if (null != parameterName && null != companyId)
			return parameterName.hashCode() + companyId.hashCode();
		else
			return 53;
	}

	@Column(name = "createdby")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "createddatetime")
	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Column(name = "updatedby")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updateddatetime")
	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	@Column(name = "removedatetime")
	public Date getRemoveDateTime() {
		return removeDateTime;
	}

	public void setRemoveDateTime(Date removeDateTime) {
		this.removeDateTime = removeDateTime;
	}

	@Column(name = "effectivestartdate")
	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	@Column(name = "effectiveenddate")
	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

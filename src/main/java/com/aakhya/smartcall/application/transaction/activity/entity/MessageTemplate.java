package com.aakhya.smartcall.application.transaction.activity.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sc_messagetemplate")
public class MessageTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 344057861289777994L;
	private Long templateId;
	private String messageTitle;
	private Long tataTemplateId;
	private String messageType;
	private String messageString;
	private String approvalStatus;
	private String status;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private Date removeDateTime;
	private Date validFrom;
	private Date valdTo;
	private Long companyId;
	@Id
	@Column(name = "templateid")
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	@Column(name = "tatatemplateid")
	public Long getTataTemplateId() {
		return tataTemplateId;
	}
	public void setTataTemplateId(Long tataTemplateId) {
		this.tataTemplateId = tataTemplateId;
	}
	@Column(name = "messagetype")
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	@Column(name = "messagestring")
	public String getMessageString() {
		return messageString;
	}
	public void setMessageString(String messageString) {
		this.messageString = messageString;
	}
	@Column(name = "approvalstatus")
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Column(name = "validfrom")
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	@Column(name = "validto")
	public Date getValdTo() {
		return valdTo;
	}
	public void setValdTo(Date valdTo) {
		this.valdTo = valdTo;
	}
	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	@Column(name = "messagetitle")
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
}

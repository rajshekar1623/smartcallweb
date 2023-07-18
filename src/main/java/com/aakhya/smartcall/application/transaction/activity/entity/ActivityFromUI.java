package com.aakhya.smartcall.application.transaction.activity.entity;

import java.io.Serializable;
import java.util.Date;

public class ActivityFromUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3703722391079348434L;
	private Long activityId;
	private Long companyId;
	private Long activityType;
	private String activityDescription;
	private Date activityDateTime;
	private Long dataSetId;
	private Long parentActivity;
	private String branchCode;
	private String userId;
	private Long attemptSequence;
	private Date attemptDateTime;
	private Integer attemptDuration;
	private String attemptStatus;
	private String attemptFlow;
	private String attemptNotes;
	private String scheduleType;
	private Date scheduleDateTime;
	private String status;
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getActivityType() {
		return activityType;
	}
	public void setActivityType(Long activityType) {
		this.activityType = activityType;
	}
	public String getActivityDescription() {
		return activityDescription;
	}
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	public Date getActivityDateTime() {
		return activityDateTime;
	}
	public void setActivityDateTime(Date activityDateTime) {
		this.activityDateTime = activityDateTime;
	}
	public Long getDataSetId() {
		return dataSetId;
	}
	public void setDataSetId(Long dataSetId) {
		this.dataSetId = dataSetId;
	}
	public Long getParentActivity() {
		return parentActivity;
	}
	public void setParentActivity(Long parentActivity) {
		this.parentActivity = parentActivity;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getAttemptSequence() {
		return attemptSequence;
	}
	public void setAttemptSequence(Long attemptSequence) {
		this.attemptSequence = attemptSequence;
	}
	public Date getAttemptDateTime() {
		return attemptDateTime;
	}
	public void setAttemptDateTime(Date attemptDateTime) {
		this.attemptDateTime = attemptDateTime;
	}
	public Integer getAttemptDuration() {
		return attemptDuration;
	}
	public void setAttemptDuration(Integer attemptDuration) {
		this.attemptDuration = attemptDuration;
	}
	public String getAttemptStatus() {
		return attemptStatus;
	}
	public void setAttemptStatus(String attemptStatus) {
		this.attemptStatus = attemptStatus;
	}
	public String getAttemptFlow() {
		return attemptFlow;
	}
	public void setAttemptFlow(String attemptFlow) {
		this.attemptFlow = attemptFlow;
	}
	public String getAttemptNotes() {
		return attemptNotes;
	}
	public void setAttemptNotes(String attemptNotes) {
		this.attemptNotes = attemptNotes;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public Date getScheduleDateTime() {
		return scheduleDateTime;
	}
	public void setScheduleDateTime(Date scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

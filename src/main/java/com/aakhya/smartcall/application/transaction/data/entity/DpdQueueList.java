package com.aakhya.smartcall.application.transaction.data.entity;

import java.io.Serializable;

public class DpdQueueList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7393106215365578431L;
	private Long dataSetId;
	private String memberName;
	private String location;
	private Double distance;
	private String scheduleDateTime;
	private Double lattitute;
	private Double longitute;
	private String actionStatus;
	private String mobileNumber;
	private String pinCode;
	
	public Long getDataSetId() {
		return dataSetId;
	}
	public void setDataSetId(Long dataSetId) {
		this.dataSetId = dataSetId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getScheduleDateTime() {
		return scheduleDateTime;
	}
	public void setScheduleDateTime(String scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}
	public Double getLattitute() {
		return lattitute;
	}
	public void setLattitute(Double lattitute) {
		this.lattitute = lattitute;
	}
	public Double getLongitute() {
		return longitute;
	}
	public void setLongitute(Double longitute) {
		this.longitute = longitute;
	}
	public String getActionStatus() {
		return actionStatus;
	}
	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
}

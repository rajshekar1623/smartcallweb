package com.aakhya.smartcall.application.dashboard.entity;

import java.io.Serializable;

public class FieldVisitByUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1595322311159289875L;
	private String employeeId;
	private String userName;
	private String accountNumber;
	private String customerName;
	private Double branchLat;
	private Double branchLon;
	private String meetingDate;
	private Double meetingLat;
	private Double meetingLon;
	private Double distanceFromBranch;
	private Double custLat;
	private Double custLon;
	private Double variance;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getBranchLat() {
		return branchLat;
	}
	public void setBranchLat(Double branchLat) {
		this.branchLat = branchLat;
	}
	public Double getBranchLon() {
		return branchLon;
	}
	public void setBranchLon(Double branchLon) {
		this.branchLon = branchLon;
	}
	public String getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}
	public Double getMeetingLat() {
		return meetingLat;
	}
	public void setMeetingLat(Double meetingLat) {
		this.meetingLat = meetingLat;
	}
	public Double getMeetingLon() {
		return meetingLon;
	}
	public void setMeetingLon(Double meetingLon) {
		this.meetingLon = meetingLon;
	}
	public Double getDistanceFromBranch() {
		return distanceFromBranch;
	}
	public void setDistanceFromBranch(Double distanceFromBranch) {
		this.distanceFromBranch = distanceFromBranch;
	}
	public Double getCustLat() {
		return custLat;
	}
	public void setCustLat(Double custLat) {
		this.custLat = custLat;
	}
	public Double getCustLon() {
		return custLon;
	}
	public void setCustLon(Double custLon) {
		this.custLon = custLon;
	}
	public Double getVariance() {
		return variance;
	}
	public void setVariance(Double variance) {
		this.variance = variance;
	}
}

package com.aakhya.smartcall.application.dashboard.entity;

import java.io.Serializable;

public class ActivitySummaryUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5464113945611900892L;
	private String branchName;
	private String userName;
	private Integer noOfAccountsAssigned;
	private Integer noOfAcsCalledOnes;
	private Integer noOfAcsCalledTwice;
	private Integer noOfAcsCalledThrice;
	private Integer noOfAcsCalledMoreThanThrice;
	private Integer notCalled;
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getNoOfAccountsAssigned() {
		return noOfAccountsAssigned;
	}
	public void setNoOfAccountsAssigned(Integer noOfAccountsAssigned) {
		this.noOfAccountsAssigned = noOfAccountsAssigned;
	}
	public Integer getNoOfAcsCalledOnes() {
		return noOfAcsCalledOnes;
	}
	public void setNoOfAcsCalledOnes(Integer noOfAcsCalledOnes) {
		this.noOfAcsCalledOnes = noOfAcsCalledOnes;
	}
	public Integer getNoOfAcsCalledTwice() {
		return noOfAcsCalledTwice;
	}
	public void setNoOfAcsCalledTwice(Integer noOfAcsCalledTwice) {
		this.noOfAcsCalledTwice = noOfAcsCalledTwice;
	}
	public Integer getNoOfAcsCalledThrice() {
		return noOfAcsCalledThrice;
	}
	public void setNoOfAcsCalledThrice(Integer noOfAcsCalledThrice) {
		this.noOfAcsCalledThrice = noOfAcsCalledThrice;
	}
	public Integer getNoOfAcsCalledMoreThanThrice() {
		return noOfAcsCalledMoreThanThrice;
	}
	public void setNoOfAcsCalledMoreThanThrice(Integer noOfAcsCalledMoreThanThrice) {
		this.noOfAcsCalledMoreThanThrice = noOfAcsCalledMoreThanThrice;
	}
	public Integer getNotCalled() {
		return notCalled;
	}
	public void setNotCalled(Integer notCalled) {
		this.notCalled = notCalled;
	}
}

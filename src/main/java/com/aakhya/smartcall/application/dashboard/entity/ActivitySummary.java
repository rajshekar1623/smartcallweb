package com.aakhya.smartcall.application.dashboard.entity;

import java.io.Serializable;

public class ActivitySummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3113510685793295630L;
	private String branchName;
	private Integer noOfAccountsAssigned;
	private Integer noOfAcsCalledOnes;
	private Integer noOfAcsCalledTwice;
	private Integer noOfAcsCalledThrice;
	private Integer noOfAcsCalledMoreThanThrice;
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	
}

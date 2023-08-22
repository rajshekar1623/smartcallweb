package com.aakhya.smartcall.application.transaction.data.entity;

import java.io.Serializable;

public class DpdQueue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3768076729597469398L;
	private String dpdQueueName;
	private Integer noOfMembers;
	private Integer completed;
	private Integer pending;
	private Integer inprocess;
	public Integer getNoOfMembers() {
		return noOfMembers;
	}
	public void setNoOfMembers(Integer noOfMembers) {
		this.noOfMembers = noOfMembers;
	}
	public String getDpdQueueName() {
		return dpdQueueName;
	}
	public void setDpdQueueName(String dpdQueueName) {
		this.dpdQueueName = dpdQueueName;
	}
	public Integer getCompleted() {
		return completed;
	}
	public void setCompleted(Integer completed) {
		this.completed = completed;
	}
	public Integer getPending() {
		return pending;
	}
	public void setPending(Integer pending) {
		this.pending = pending;
	}
	public Integer getInprocess() {
		return inprocess;
	}
	public void setInprocess(Integer inprocess) {
		this.inprocess = inprocess;
	}
	
	
}

package com.aakhya.smartcall.application.transaction.activity.entity;

import java.io.Serializable;

public class ScheduleForTheDay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3276967990219271676L;
	private String queue;
	private Integer pending;
	private Integer complete;
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public Integer getPending() {
		return pending;
	}
	public void setPending(Integer pending) {
		this.pending = pending;
	}
	public Integer getComplete() {
		return complete;
	}
	public void setComplete(Integer complete) {
		this.complete = complete;
	}
}

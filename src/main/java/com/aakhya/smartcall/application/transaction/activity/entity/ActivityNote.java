package com.aakhya.smartcall.application.transaction.activity.entity;

import java.io.Serializable;

public class ActivityNote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8539283444991370626L;
	private String userName;
	private String date;
	private String time;
	private String activityType;
	private String notes;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}

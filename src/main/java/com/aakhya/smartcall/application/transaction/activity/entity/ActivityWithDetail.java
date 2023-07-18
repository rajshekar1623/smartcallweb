package com.aakhya.smartcall.application.transaction.activity.entity;

import java.io.Serializable;
import java.util.List;

public class ActivityWithDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1105309833072105763L;
	private String activityDate;
	private String activityTime;
	private String activityDay;
	private String userName;
	private String activityType;
	private List<String> steps;
	private String scheduleType;
	private String scheduleDateTime;
	private String amountCollected;
	public String getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	public String getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}
	public String getActivityDay() {
		return activityDay;
	}
	public void setActivityDay(String activityDay) {
		this.activityDay = activityDay;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public List<String> getSteps() {
		return steps;
	}
	public void setSteps(List<String> steps) {
		this.steps = steps;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public String getScheduleDateTime() {
		return scheduleDateTime;
	}
	public void setScheduleDateTime(String scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}
	public String getAmountCollected() {
		return amountCollected;
	}
	public void setAmountCollected(String amountCollected) {
		this.amountCollected = amountCollected;
	}

}

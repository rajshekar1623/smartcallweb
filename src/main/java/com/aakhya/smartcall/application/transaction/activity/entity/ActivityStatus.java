package com.aakhya.smartcall.application.transaction.activity.entity;

public enum ActivityStatus {
	INPROCESS("IN-PROCESS"),PENDING("PENDING"),COMPLETE("COMPLETE");
	private String value;
	
	ActivityStatus(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}

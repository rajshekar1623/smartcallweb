package com.aakhya.smartcall.application.transaction.activity.entity;

public enum ActivityTypeStr {
	ASSIGN("ASSIGN"),CALL("CALL"),VISIT("VISIT"),SCHEDULE("SCHEDULE");
	
	private String value;
	
	ActivityTypeStr(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}

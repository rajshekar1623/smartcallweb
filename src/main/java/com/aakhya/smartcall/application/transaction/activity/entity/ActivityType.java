package com.aakhya.smartcall.application.transaction.activity.entity;

public enum ActivityType {

	ASSIGN(1001L),CALL(1002L),VISIT(1003L),SCHEDULE(1004L);
	
	private Long value;
	
	ActivityType(Long value){
		this.value = value;
	}
	
	public Long getValue() {
		return value;
	}
}

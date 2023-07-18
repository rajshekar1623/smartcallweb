package com.aakhya.smartcall.application.admin.entity;

public enum RecordStatusType {
	ACTIVE("A"),INACTIVE("C"),DELETED("X");

	private String value;
	
	RecordStatusType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}

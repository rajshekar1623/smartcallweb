package com.aakhya.smartcall.application.admin.entity;

public enum GenericKeyType {
	BRANCH_TYPE("BRNTYPE"),BRANCH_CAT("BRNCAT"),
	GENDER("GENDER"),SOCIAL_CAT("SOCIALCAT"),RELIGION("RELIGION"),
	GRAMPANCHAYAT("GRAMPANCH"),VILLAGE("VILLAGECD");

	private String value;
	
	GenericKeyType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}

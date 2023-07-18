package com.aakhya.smartcall.application.admin.entity;

public enum EntityNameType {
	ADMIN_GENERIC_CLASSIFIER("sc_genericClassifier"),  
	BRANCH_MASTER("sc_branch"), 
	ROLE("sc_role"),
	INTEGRATION_MASTER("sc_integrationMaster"),
	TRANS_DATA_SET("sc_transactiondataset"),
	ACTIVITY("sc_activity"),
	BUSINESS_UNIT("sc_businessunit"),
	PROCESS_UNIT("sc_processingunit"),
	MESSAGE_TEMPLATE("sc_messagetemplate");

	private String value;

	EntityNameType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}

package com.aakhya.smartcall.application.integration.entity;

public enum IntegrationFileFormatType {
	FIXED_LENGTH_TXT("FIXED_LENGTH_TEXT_FILE"),
	DILIMITED_TXT("DILIMITED_TEXT_FILE"),
	XML_FILE("XML_FILE"),JSON_FILE("JASON_FILE");
	
	private String value;

	IntegrationFileFormatType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}

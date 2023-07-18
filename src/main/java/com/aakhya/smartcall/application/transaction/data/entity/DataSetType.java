package com.aakhya.smartcall.application.transaction.data.entity;

public enum DataSetType {
	LEAD(1L),CUSTOMER_FOR_COLL(2L),CUSTOMER_NEW(3L);
	
	private Long value;

	DataSetType(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return this.value;
	}
}

package com.aakhya.smartcall.application.transaction.ui;

import java.io.Serializable;

public class DataSetException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -104933208815238948L;
	private String accountNumber;
	private Object exceptionRecordValue;
	public DataSetException(String accountNumber, Object exceptionRecordValue) {
		super();
		this.accountNumber = accountNumber;
		this.exceptionRecordValue = exceptionRecordValue;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Object getExceptionRecordValue() {
		return exceptionRecordValue;
	}
	public void setExceptionRecordValue(Object exceptionRecordValue) {
		this.exceptionRecordValue = exceptionRecordValue;
	}
}
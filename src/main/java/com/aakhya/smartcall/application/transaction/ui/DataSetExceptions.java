package com.aakhya.smartcall.application.transaction.ui;

import java.io.Serializable;

public class DataSetExceptions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -608046272260628115L;
	private String columnName;
	private Integer noOfValidRecords;
	private Integer noOfInvalidRecords;
	public DataSetExceptions(String columnName, Integer noOfValidRecords, Integer noOfInvalidRecords) {
		super();
		this.columnName = columnName;
		this.noOfValidRecords = noOfValidRecords;
		this.noOfInvalidRecords = noOfInvalidRecords;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Integer getNoOfValidRecords() {
		return noOfValidRecords;
	}
	public void setNoOfValidRecords(Integer noOfValidRecords) {
		this.noOfValidRecords = noOfValidRecords;
	}
	public Integer getNoOfInvalidRecords() {
		return noOfInvalidRecords;
	}
	public void setNoOfInvalidRecords(Integer noOfInvalidRecords) {
		this.noOfInvalidRecords = noOfInvalidRecords;
	}
	
}

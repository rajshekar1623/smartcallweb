package com.aakhya.smartcall.application.transaction.data.entity;

import java.io.Serializable;
import java.util.List;

public class TransactionDataUploadEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5756175588198723626L;
	private List<TransactionDataSet> uploadedData;
	private Long initialCount;
	private Long updateCount;
	private Long createCount;
	public List<TransactionDataSet> getUploadedData() {
		return uploadedData;
	}
	public void setUploadedData(List<TransactionDataSet> uploadedData) {
		this.uploadedData = uploadedData;
	}
	public Long getInitialCount() {
		return initialCount;
	}
	public void setInitialCount(Long initialCount) {
		this.initialCount = initialCount;
	}
	public Long getUpdateCount() {
		return updateCount;
	}
	public void setUpdateCount(Long updateCount) {
		this.updateCount = updateCount;
	}
	public Long getCreateCount() {
		return createCount;
	}
	public void setCreateCount(Long createCount) {
		this.createCount = createCount;
	}
}
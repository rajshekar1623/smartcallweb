package com.aakhya.smartcall.application.transaction.data.entity;

import java.io.Serializable;

public class DetailViewConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -218908642271857221L;
	private Long queue;
	private Integer sequence;
	private String fieldName;
	private String fieldLable;
	private String columFunction;
	private String editable;
	private String editDataType;
	private String button;
	private String buttonLable;
	private String buttonAction;
	public Long getQueue() {
		return queue;
	}
	public void setQueue(Long queue) {
		this.queue = queue;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldLable() {
		return fieldLable;
	}
	public void setFieldLable(String fieldLable) {
		this.fieldLable = fieldLable;
	}
	public String getColumFunction() {
		return columFunction;
	}
	public void setColumFunction(String columFunction) {
		this.columFunction = columFunction;
	}
	public String getEditable() {
		return editable;
	}
	public void setEditable(String editable) {
		this.editable = editable;
	}
	public String getEditDataType() {
		return editDataType;
	}
	public void setEditDataType(String editDataType) {
		this.editDataType = editDataType;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	public String getButtonLable() {
		return buttonLable;
	}
	public void setButtonLable(String buttonLable) {
		this.buttonLable = buttonLable;
	}
	public String getButtonAction() {
		return buttonAction;
	}
	public void setButtonAction(String buttonAction) {
		this.buttonAction = buttonAction;
	}
}

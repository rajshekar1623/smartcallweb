package com.aakhya.smartcall.application.admin.service.utils;

import java.io.Serializable;

public class DurationInTraffic implements Serializable{
    private static final long serialVersionUID = 158098995163835272L;
	public String text;
    public int value;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}

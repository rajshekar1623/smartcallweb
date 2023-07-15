package com.aakhya.smartcall.application;

import com.vaadin.flow.component.formlayout.FormLayout;

public abstract class SmartCallWebForm extends FormLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1595705512426928309L;
	
	public abstract void save();
	public abstract void delete();
}

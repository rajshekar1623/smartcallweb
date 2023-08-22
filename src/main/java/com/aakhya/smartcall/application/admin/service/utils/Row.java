package com.aakhya.smartcall.application.admin.service.utils;

import java.io.Serializable;
import java.util.ArrayList;

public class Row implements Serializable{
    private static final long serialVersionUID = -5900445237767771123L;
	public ArrayList<Element> elements;

	public ArrayList<Element> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Element> elements) {
		this.elements = elements;
	}
}

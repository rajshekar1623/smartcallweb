package com.aakhya.smartcall.application.transaction.ui;

import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.admin.entity.GenericKeyType;
import com.aakhya.smartcall.application.admin.service.GenericClassifierService;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class TransactionDataSetView extends FormLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4609591422147725026L;
	private TransactionDataSet transactionDataSet;
	private GenericClassifierService genericClassifierService;
	TextField firstName = new TextField("First Name");
	TextField lastName = new TextField("Last Name");
	DatePicker dateOfBirth = new DatePicker("Date of Birth");
	ComboBox<GenericClassifier> genderDesc = new ComboBox<GenericClassifier>("Gender");
	ComboBox<GenericClassifier> socialCategoryDesc = new ComboBox<GenericClassifier>("Social Category");
	ComboBox<GenericClassifier> religionDesc = new ComboBox<GenericClassifier>("Religion");
	TextField voterId = new TextField("Voter Id");
	TextField drivingLicenseNumber = new TextField("Driving License No.");
	ComboBox<GenericClassifier> grampanchayatDesc = new ComboBox<GenericClassifier>("Grampanchayat");
	ComboBox<GenericClassifier> villageDesc = new ComboBox<GenericClassifier>("Village");
	TextField genericNumber2 = new TextField("Loan Account #");
	Binder<TransactionDataSet> binder = new BeanValidationBinder<>(TransactionDataSet.class);

	TransactionDataSetView(TransactionDataSet transactionDataSet,
			GenericClassifierService genericClassifierService){
		this.transactionDataSet = transactionDataSet;
		this.genericClassifierService = genericClassifierService;
		addClassName("transactionDataSetForm-form");
//		getStyle().set( "border" , "2px solid Grey" ) ; 
//        getStyle().set("border-radius", "10px");
//        getStyle().set("padding", "20px");
		buildLayout();
	}
	
	private void buildLayout() {
		this.setSizeFull();
		genderDesc.setItems(genericClassifierService.findByGenericKey(GenericKeyType.GENDER.getValue()));
		socialCategoryDesc.setItems(genericClassifierService.findByGenericKey(GenericKeyType.SOCIAL_CAT.getValue()));
		religionDesc.setItems(genericClassifierService.findByGenericKey(GenericKeyType.RELIGION.getValue()));
		genderDesc.setItems(genericClassifierService.findByGenericKey(GenericKeyType.GRAMPANCHAYAT.getValue()));
		genderDesc.setItems(genericClassifierService.findByGenericKey(GenericKeyType.VILLAGE.getValue()));
		add(firstName,lastName,dateOfBirth,genderDesc,socialCategoryDesc,religionDesc,voterId,drivingLicenseNumber,grampanchayatDesc,villageDesc,genericNumber2);
		binder.bindInstanceFields(this);
		binder.readBean(transactionDataSet);
	}
	
}

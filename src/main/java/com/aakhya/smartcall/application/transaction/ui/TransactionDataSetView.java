package com.aakhya.smartcall.application.transaction.ui;

import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.admin.entity.GenericKeyType;
import com.aakhya.smartcall.application.admin.service.GenericClassifierService;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.aakhya.smartcall.application.transaction.data.service.TransactionDataSetService;
import com.aakhyatech.smartcall.application.utils.BigDecimalToDoubleConverter;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class TransactionDataSetView extends FormLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4609591422147725026L;
	private AssignmentView parent;
	private ActivityStatusView statusParent;
	private TransactionDataSetService transactionDataSetService;
	private TransactionDataSet transactionDataSet;
	private GenericClassifierService genericClassifierService;
	TextField firstName = new TextField("Customer Name");
//	TextField lastName = new TextField("Last Name");
	DatePicker dateOfBirth = new DatePicker("Date of Birth");
	ComboBox<GenericClassifier> genderDesc = new ComboBox<GenericClassifier>("Gender");
//	ComboBox<GenericClassifier> socialCategoryDesc = new ComboBox<GenericClassifier>("Social Category");
//	ComboBox<GenericClassifier> religionDesc = new ComboBox<GenericClassifier>("Religion");
	TextField panCardNumber = new TextField("Pan Card Number");
//	TextField drivingLicenseNumber = new TextField("Driving License No.");
//	ComboBox<GenericClassifier> grampanchayatDesc = new ComboBox<GenericClassifier>("Grampanchayat");
//	ComboBox<GenericClassifier> villageDesc = new ComboBox<GenericClassifier>("Village");
	TextArea genericString5 = new TextArea("Address");
	TextField pincode = new TextField("Pincode");
	TextField mobileNumber = new TextField("Mobile Number");
	TextField loanAccountNumber = new TextField("Loan Account #");
	NumberField genericDecimal4 = new NumberField("Outstanding Loan Acount");
	NumberField genericDecimal5 = new NumberField("Interest Due");
	NumberField genericDecimal6 = new NumberField("Principle Due");
	NumberField genericDecimal7 = new NumberField("Interest Rate");
	Binder<TransactionDataSet> binder = new BeanValidationBinder<>(TransactionDataSet.class);

	TransactionDataSetView(AssignmentView parent,TransactionDataSetService transactionDataSetService,TransactionDataSet transactionDataSet,
			GenericClassifierService genericClassifierService){
		this.parent = parent;
		this.transactionDataSetService = transactionDataSetService;
		this.transactionDataSet = transactionDataSet;
		this.genericClassifierService = genericClassifierService;
		addClassName("transactionDataSetForm-form");
//		getStyle().set( "border" , "2px solid Grey" ) ; 
//        getStyle().set("border-radius", "10px");
//        getStyle().set("padding", "20px");
		buildLayout();
	}
	TransactionDataSetView(ActivityStatusView statusParent,TransactionDataSetService transactionDataSetService,TransactionDataSet transactionDataSet,
			GenericClassifierService genericClassifierService){
		this.statusParent = statusParent;
		this.transactionDataSetService = transactionDataSetService;
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
		genderDesc.setItemLabelGenerator(GenericClassifier::getDescription);

//		socialCategoryDesc.setItems(genericClassifierService.findByGenericKey(GenericKeyType.SOCIAL_CAT.getValue()));
//		religionDesc.setItems(genericClassifierService.findByGenericKey(GenericKeyType.RELIGION.getValue()));
		genericString5.setHeight("120px");
		loanAccountNumber.setReadOnly(true);
		binder.forField(genericDecimal4)
        .withConverter(new BigDecimalToDoubleConverter())
        .bind(TransactionDataSet::getGenericDecimal4, TransactionDataSet::setGenericDecimal4);
		binder.forField(genericDecimal5)
        .withConverter(new BigDecimalToDoubleConverter())
        .bind(TransactionDataSet::getGenericDecimal5, TransactionDataSet::setGenericDecimal5);
		binder.forField(genericDecimal6)
        .withConverter(new BigDecimalToDoubleConverter())
        .bind(TransactionDataSet::getGenericDecimal6, TransactionDataSet::setGenericDecimal6);
		binder.forField(genericDecimal7)
        .withConverter(new BigDecimalToDoubleConverter())
        .bind(TransactionDataSet::getGenericDecimal7, TransactionDataSet::setGenericDecimal7);
		add(firstName,dateOfBirth,genderDesc,panCardNumber,genericString5,pincode,mobileNumber,
				loanAccountNumber,genericDecimal4,genericDecimal5,genericDecimal6,genericDecimal7);
		binder.bindInstanceFields(this);
//		binder.readBean(transactionDataSet);
		binder.setBean(transactionDataSet);
	}
	
	public void save() {
		binder.validate();
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Save Transaction data");
		dialog.setText("Are you sure you want to update?");
		dialog.setCancelable(true);
		dialog.setConfirmText("Assign");
		dialog.addConfirmListener(event -> finalSave());
		dialog.open();
		TransactionDataSet tds = binder.getBean();
		transactionDataSetService.save(tds);
	}

	private void finalSave() {
		TransactionDataSet tds = binder.getBean();
		transactionDataSetService.save(tds);
		parent.updateList();
		Notification.show("Saved successfully", 15000, Position.MIDDLE);
	}
}

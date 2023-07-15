package com.aakhya.smartcall.application.transaction.ui;

import com.aakhya.smartcall.application.SmartCallWebForm;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class AssignmentForm extends SmartCallWebForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30588516090628260L;
	private static final String objectName = "Assignment";
	TextField firstName = new TextField("First Name");
	TextField middleName = new TextField("Middle Name");
	TextField lastName = new TextField("lastName");
	DatePicker dateOfBirth = new DatePicker("Date of Birth");
	TextField nameOfMother = new TextField("Mothers Name");
	TextField mobileNumber = new TextField("Mobile Number");
//	
//	DatePicker validFrom = new DatePicker("Valid From");
//	DatePicker validTo = new DatePicker("Valid Until");

	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	Binder<TransactionDataSet> binder;
	private TransactionDataSet transactionDataSet;

	public AssignmentForm() {

		addClassName("branch-form");
		getStyle().set( "border" , "2px solid Grey" ) ; 
		getStyle().set("border-radius", "10px");
		getStyle().set("padding", "20px");
//		userId.setRequired(true);
//		userName.setRequired(true);
//		password.setRequired(true);
		binder = new BeanValidationBinder<>(TransactionDataSet.class);
		binder.bindInstanceFields(this);
		
		add(firstName, middleName, lastName, dateOfBirth, 
				nameOfMother,mobileNumber,createButtonsLayout());
	}

	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);

		save.addClickListener(event -> validateAndSave());
		delete.addClickListener(event -> MessageUtils.confirmDeleteMessageBox(this, objectName));
		close.addClickListener(event -> fireEvent(new CloseEvent(this)));

		binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
		return new HorizontalLayout(save, delete, close);
	}

	private void validateAndSave() {
		try {
			binder.writeBean(transactionDataSet);
			
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			fireEvent(new SaveEvent(this, transactionDataSet));
			MessageUtils.showSaveNotification(objectName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			fireEvent(new DeleteEvent(this, transactionDataSet));
			MessageUtils.showDeleteNotification(objectName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setTransactionDataSet(TransactionDataSet transactionDataSet) {
		this.transactionDataSet = transactionDataSet;
		binder.readBean(transactionDataSet);
	}

//Events
	public static abstract class AssignmentFormEvent extends ComponentEvent<AssignmentForm> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6082433833350699897L;
		private TransactionDataSet transactionDataSet;

		protected AssignmentFormEvent(AssignmentForm source, TransactionDataSet transactionDataSet) {
			super(source, false);
			this.transactionDataSet = transactionDataSet;
		}

		public TransactionDataSet getTransactionDataSet() {
			return transactionDataSet;
		}
	}

	public static class SaveEvent extends AssignmentFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4405498981813599396L;

		SaveEvent(AssignmentForm source, TransactionDataSet transactionDataSet) {
			super(source, transactionDataSet);
		}
	}

	public static class DeleteEvent extends AssignmentFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8418559976585902363L;

		DeleteEvent(AssignmentForm source, TransactionDataSet transactionDataSet) {
			super(source, transactionDataSet);
		}

	}

	public static class CloseEvent extends AssignmentFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6300891543447052361L;

		CloseEvent(AssignmentForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
	
}
package com.aakhya.smartcall.application.admin.ui;

import com.aakhya.smartcall.application.SmartCallWebForm;
import com.aakhya.smartcall.application.admin.entity.GenericKey;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class GenericKeyForm extends SmartCallWebForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30588516090628260L;
	private static final String objectName = "Generic Key";
	TextField genericKey = new TextField("Generic Key");
	TextField description = new TextField("description");
	DatePicker validFrom = new DatePicker("Valid From");
	DatePicker validTo = new DatePicker("Valid Until");
	

	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	Binder<GenericKey> binder = new BeanValidationBinder<>(GenericKey.class);
	private GenericKey genKey;

	public GenericKeyForm() {

		addClassName("generickey-form");
		
		
		
		getStyle().set( "border" , "2px solid Grey" ) ; 
        getStyle().set("border-radius", "10px");
        getStyle().set("padding", "20px");
		binder.bindInstanceFields(this);

		add(genericKey, description, validFrom, validTo,createButtonsLayout());
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
			binder.writeBean(genKey);
			MessageUtils.confirmSaveMessageBox(this, objectName);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			fireEvent(new SaveEvent(this, genKey));
			MessageUtils.showSaveNotification(objectName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			fireEvent(new DeleteEvent(this, genKey));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setGenericKey(GenericKey genKey) {
		this.genKey = genKey;
		binder.readBean(genKey);
	}

//Events
	public static abstract class BranchFormEvent extends ComponentEvent<GenericKeyForm> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8256491527189968685L;
		private GenericKey genKey;

		protected BranchFormEvent(GenericKeyForm source, GenericKey genKey) {
			super(source, false);
			this.genKey = genKey;
		}

		public GenericKey getGenericKey() {
			return genKey;
		}
	}

	public static class SaveEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1918003555075373170L;

		SaveEvent(GenericKeyForm source, GenericKey genKey) {
			super(source, genKey);
		}
	}

	public static class DeleteEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = -710845817912666000L;

		DeleteEvent(GenericKeyForm source, GenericKey genKey) {
			super(source, genKey);
		}

	}

	public static class CloseEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = -236400317134727310L;

		CloseEvent(GenericKeyForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
	
}
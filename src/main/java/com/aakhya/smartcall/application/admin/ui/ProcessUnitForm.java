package com.aakhya.smartcall.application.admin.ui;

import com.aakhya.smartcall.application.admin.entity.ProcessUnit;
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

public class ProcessUnitForm extends FormLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30588516090628260L;
	TextField processingUnitName = new TextField("Processing Unit Name");
	//TextField description = new TextField("description");
	DatePicker effectiveStartDate = new DatePicker("Valid From");
	DatePicker effectiveEndDate = new DatePicker("Valid Until");
	

	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	Binder<ProcessUnit> binder = new BeanValidationBinder<>(ProcessUnit.class);
	private ProcessUnit processUnit;

	public ProcessUnitForm() {

		addClassName("processunit-form");
		getStyle().set( "border" , "2px solid Grey" ) ; 
        getStyle().set("border-radius", "10px");
        getStyle().set("padding", "20px");
		binder.bindInstanceFields(this);

		add(processingUnitName, effectiveStartDate, effectiveEndDate,createButtonsLayout());

	}

	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);

		save.addClickListener(event -> validateAndSave());
		delete.addClickListener(event -> fireEvent(new DeleteEvent(this, processUnit)));
		close.addClickListener(event -> fireEvent(new CloseEvent(this)));

		binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
		return new HorizontalLayout(save, delete, close);
	}

	private void validateAndSave() {
		try {
			binder.writeBean(processUnit);
			fireEvent(new SaveEvent(this, processUnit));
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	public void setProcessUnit(ProcessUnit processUnit) {
		this.processUnit = processUnit;
		binder.readBean(processUnit);
	}

//Events
	public static abstract class ProcessUnitFormEvent extends ComponentEvent<ProcessUnitForm> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8256491527189968685L;
		private ProcessUnit processUnit;

		protected ProcessUnitFormEvent(ProcessUnitForm source, ProcessUnit processUnit) {
			super(source, false);
			this.processUnit = processUnit;
		}

		public ProcessUnit getProcessUnit() {
			return processUnit;
		}
	}

	public static class SaveEvent extends ProcessUnitFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1918003555075373170L;

		SaveEvent(ProcessUnitForm source, ProcessUnit processUnit) {
			super(source, processUnit);
		}
	}

	public static class DeleteEvent extends ProcessUnitFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = -710845817912666000L;

		DeleteEvent(ProcessUnitForm source, ProcessUnit processUnit) {
			super(source, processUnit);
		}

	}

	public static class CloseEvent extends ProcessUnitFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = -236400317134727310L;

		CloseEvent(ProcessUnitForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
	
}
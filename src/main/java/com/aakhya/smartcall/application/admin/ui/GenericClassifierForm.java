package com.aakhya.smartcall.application.admin.ui;

import java.util.List;

import com.aakhya.smartcall.application.SmartCallWebForm;
import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.admin.entity.GenericKey;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class GenericClassifierForm extends SmartCallWebForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7351826825774060217L;
	private static final String objectName = "GenericClassifer";
	TextField description = new TextField("Description");
	ComboBox<GenericKey> key = new ComboBox<>("Genric Key");
	ComboBox<GenericClassifier> parentKeyObject = new ComboBox<>("Parent Key");
	DatePicker validFrom = new DatePicker("Valid From");
	DatePicker validTo = new DatePicker("Valid Until");

	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	Binder<GenericClassifier> binder = new BeanValidationBinder<>(GenericClassifier.class);
	private GenericClassifier genericClassifier;

	public GenericClassifierForm(List<GenericKey> genericKeys, List<GenericClassifier> parentKeys) {

		addClassName("genericClassifier-form");
		getStyle().set( "border" , "2px solid Grey" ) ; 
        getStyle().set("border-radius", "10px");
        getStyle().set("padding", "20px");
		binder.bindInstanceFields(this);
		key.setItems(genericKeys);
		key.setItemLabelGenerator(GenericKey::getDescription);
		parentKeyObject.setItems(parentKeys);
		parentKeyObject.setItemLabelGenerator(GenericClassifier::getDescription);
		setSizeFull();
		HorizontalLayout buttonLayout = createButtonsLayout();
		add(description, key, parentKeyObject, validFrom, validTo, buttonLayout);
		setResponsiveSteps(
		        // Use one column by default
		        new ResponsiveStep("0", 1),
		        // Use two columns, if the layout's width exceeds 320px
		        new ResponsiveStep("320px", 2),
		        // Use three columns, if the layout's width exceeds 500px
		        new ResponsiveStep("500px", 3));
		setColspan(buttonLayout, 2);
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
			binder.writeBean(genericClassifier);
			MessageUtils.confirmSaveMessageBox(this, objectName);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			fireEvent(new SaveEvent(this, genericClassifier));
			MessageUtils.showSaveNotification(objectName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			fireEvent(new DeleteEvent(this, genericClassifier));
			MessageUtils.showDeleteNotification(objectName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setGenericClassifier(GenericClassifier genericClassifier) {
		this.genericClassifier = genericClassifier;
		binder.readBean(genericClassifier);
	}

//Events
	public static abstract class GenericClassifierFormEvent extends ComponentEvent<GenericClassifierForm> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7328708410563570113L;
		private GenericClassifier genericClassifier;

		protected GenericClassifierFormEvent(GenericClassifierForm source, GenericClassifier genericClassifier) {
			super(source, false);
			this.genericClassifier = genericClassifier;
		}

		public GenericClassifier getGenericClassifier() {
			return genericClassifier;
		}
	}

	public static class SaveEvent extends GenericClassifierFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1805354494515349658L;

		SaveEvent(GenericClassifierForm source, GenericClassifier genericClassifier) {
			super(source, genericClassifier);
		}
	}

	public static class DeleteEvent extends GenericClassifierFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3355646415128567919L;

		DeleteEvent(GenericClassifierForm source, GenericClassifier genericClassifier) {
			super(source, genericClassifier);
		}

	}

	public static class CloseEvent extends GenericClassifierFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4086668343158614781L;

		CloseEvent(GenericClassifierForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
	
}
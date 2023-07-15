package com.aakhya.smartcall.application.transaction.ui;

import javax.annotation.security.PermitAll;

import com.aakhya.smartcall.application.security.entity.Role;
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
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

@Route(value="transactionForm",layout = TransactionMainLayout.class)
@PermitAll
public class TransactionForm extends FormLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30588516090628260L;
	TextField description = new TextField("description");
	DatePicker validFrom = new DatePicker("Valid From");
	DatePicker validTo = new DatePicker("Valid Until");
	

	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	Binder<Role> binder = new BeanValidationBinder<>(Role.class);
	private Role genKey;

	public TransactionForm() {

		addClassName("generickey-form");
		getStyle().set( "border" , "2px solid Grey" ) ; 
		getStyle().set("border-radius", "10px");
		getStyle().set("padding", "20px");
		binder.bindInstanceFields(this);
		

		add( description, validFrom, validTo,createButtonsLayout());
	}

	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);

		save.addClickListener(event -> validateAndSave());
		delete.addClickListener(event -> fireEvent(new DeleteEvent(this, genKey)));
		close.addClickListener(event -> fireEvent(new CloseEvent(this)));

		binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
		return new HorizontalLayout(save, delete, close);
	}

	private void validateAndSave() {
		try {
			binder.writeBean(genKey);
			fireEvent(new SaveEvent(this, genKey));
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	public void setRole(Role genKey) {
		this.genKey = genKey;
		binder.readBean(genKey);
	}

//Events
	public static abstract class BranchFormEvent extends ComponentEvent<TransactionForm> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4945567293770288022L;
		private Role genKey;

		protected BranchFormEvent(TransactionForm source, Role genKey) {
			super(source, false);
			this.genKey = genKey;
		}

		public Role getRole() {
			return genKey;
		}
	}

	public static class SaveEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2938911447683153740L;

		SaveEvent(TransactionForm source, Role genKey) {
			super(source, genKey);
		}
	}

	public static class DeleteEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 167516461883114612L;

		DeleteEvent(TransactionForm source, Role genKey) {
			super(source, genKey);
		}

	}

	public static class CloseEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4437259769941637541L;

		CloseEvent(TransactionForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
	
}
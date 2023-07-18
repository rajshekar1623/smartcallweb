package com.aakhya.smartcall.application.security.ui;

import com.aakhya.smartcall.application.SmartCallWebForm;
import com.aakhya.smartcall.application.security.entity.Role;
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

public class UserRoleForm extends SmartCallWebForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30588516090628260L;
	private static final String objectName = "User Role";
	TextField description = new TextField("description");
	DatePicker validFrom = new DatePicker("Valid From");
	DatePicker validTo = new DatePicker("Valid Until");
	

	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	Binder<Role> binder = new BeanValidationBinder<>(Role.class);
	private Role genKey;

	public UserRoleForm() {

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

	public void setRole(Role genKey) {
		this.genKey = genKey;
		binder.readBean(genKey);
	}

//Events
	public static abstract class BranchFormEvent extends ComponentEvent<UserRoleForm> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4945567293770288022L;
		private Role genKey;

		protected BranchFormEvent(UserRoleForm source, Role genKey) {
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

		SaveEvent(UserRoleForm source, Role genKey) {
			super(source, genKey);
		}
	}

	public static class DeleteEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 167516461883114612L;

		DeleteEvent(UserRoleForm source, Role genKey) {
			super(source, genKey);
		}

	}

	public static class CloseEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4437259769941637541L;

		CloseEvent(UserRoleForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
	
}
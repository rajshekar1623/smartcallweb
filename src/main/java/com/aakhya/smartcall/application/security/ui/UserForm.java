package com.aakhya.smartcall.application.security.ui;

import java.util.ArrayList;
import java.util.List;

import com.aakhya.smartcall.application.SmartCallWebForm;
import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.security.entity.Role;
import com.aakhya.smartcall.application.security.entity.User;
import com.aakhya.smartcall.application.security.entity.UserRole;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class UserForm extends SmartCallWebForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30588516090628260L;
	private static final String objectName = "User";
	List<Role> rolesFromService;
	TextField userId = new TextField("User Id");
	TextField userName = new TextField("User Name");
//	PasswordField password = new PasswordField("Password");
//	ComboBox<Role> role = new ComboBox<>("User Role");
	ComboBox<Branch> branch = new ComboBox<Branch>("Branch");
	DatePicker dateOfBirth = new DatePicker("Date of Birth");
//	TextField nameOfMother = new TextField("Mothers Name");
	TextField mobileNumber = new TextField("Mobile Number");

	DatePicker validFrom = new DatePicker("Valid From");
	DatePicker validTo = new DatePicker("Valid Until");

//	TwinColSelect<Role> roles = new TwinColSelect<Role>();
	
	private Grid<Role> rolesForUser = new Grid<>(Role.class);

	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	Binder<User> binder;
	private User user;

	public UserForm(List<Role> rolesFromService, List<Branch> branches) {

		addClassName("branch-form");
		getStyle().set("border", "2px solid Grey");
		getStyle().set("border-radius", "10px");
		getStyle().set("padding", "20px");
//		userId.setRequired(true);
//		userName.setRequired(true);
//		password.setRequired(true);
		this.rolesFromService = rolesFromService;
		binder = new BeanValidationBinder<>(User.class);
		binder.bindInstanceFields(this);
//		role.setItems(rolesFromService);
//		role.setItemLabelGenerator(Role::getDescription);
		branch.setItems(branches);
		branch.setItemLabelGenerator(Branch::getBranchName);
		mobileNumber.setMaxLength(10);
//		roles.setItems(rolesFromService);
//		roles.setItemLabelGenerator(Role::getDescription);
		rolesForUser.setItems(rolesFromService);
		rolesForUser.setColumns("description");
		rolesForUser.setSelectionMode(SelectionMode.MULTI);
		TextField dummy = new TextField(" ");
		dummy.setReadOnly(true);
		add(userId, userName, //password, 
//				role, 
				branch, dateOfBirth, 
//				nameOfMother, 
				mobileNumber, validFrom, validTo,dummy,
				rolesForUser, createButtonsLayout());
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
			if(null == user.getPassword())
				user.setPassword("abcd_1234");
			binder.writeBean(user);
			MessageUtils.confirmSaveMessageBox(this, objectName);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			if(null != rolesForUser.getSelectedItems() && !rolesForUser.getSelectedItems().isEmpty()) {
				List<UserRole> userRoles = new ArrayList<UserRole>();
				for(Role role:rolesForUser.getSelectedItems()) {
					UserRole userRole = new UserRole();
					userRole.setUserId(user.getUserId());
					userRole.setRoleId(role.getRoleId());
					userRoles.add(userRole);
				}
				user.setUserRoles(userRoles);
			}
			fireEvent(new SaveEvent(this, user));
			MessageUtils.showSaveNotification(objectName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			fireEvent(new DeleteEvent(this, user));
			MessageUtils.showDeleteNotification(objectName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUser(User user) {
		this.user = user;
		rolesForUser.setItems(rolesFromService);
		binder.readBean(user);
		if (null != user && null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
			System.out.println("Roles assigned to user is :: "+user.getUserRoles().size());
			for (UserRole userRole : user.getUserRoles()) {
				Role selectedRole = new Role(userRole.getRoleId(), userRole.getRoleDescription());
				rolesForUser.select(selectedRole);
			}
		}
		System.out.println(rolesForUser.getSelectedItems().size());
	}

//Events
	public static abstract class UserFormEvent extends ComponentEvent<UserForm> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6082433833350699897L;
		private User user;

		protected UserFormEvent(UserForm source, User user) {
			super(source, false);
			this.user = user;
		}

		public User getUser() {
			return user;
		}
	}

	public static class SaveEvent extends UserFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4405498981813599396L;

		SaveEvent(UserForm source, User user) {
			
			super(source, user);
		}
	}

	public static class DeleteEvent extends UserFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8418559976585902363L;

		DeleteEvent(UserForm source, User user) {
			super(source, user);
		}

	}

	public static class CloseEvent extends UserFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6300891543447052361L;

		CloseEvent(UserForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}

	public Grid<Role> getRolesForUser() {
		return rolesForUser;
	}

	public void setRolesForUser(Grid<Role> rolesForUser) {
		this.rolesForUser = rolesForUser;
	}

}
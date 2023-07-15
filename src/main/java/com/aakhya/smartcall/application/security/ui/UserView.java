package com.aakhya.smartcall.application.security.ui;

import java.util.Set;

import javax.annotation.security.PermitAll;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.service.BranchService;
import com.aakhya.smartcall.application.security.entity.User;
import com.aakhya.smartcall.application.security.service.RoleService;
import com.aakhya.smartcall.application.security.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = "security", layout = SecurityMainLayout.class)
@PermitAll
public class UserView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2474642139276717277L;
	PaginatedGrid<User,?> grid = new PaginatedGrid<>(User.class);
	TextField filterText = new TextField("Member Name");
	ComboBox<Branch> branchCB;
	UserForm form;
	UserService service;
	RoleService roleService;
	BranchService branchService;
	SplitLayout content;

	public UserView(UserService service, RoleService roleService, BranchService branchService) {
		this.service = service;
		this.roleService = roleService;
		this.branchService = branchService;
		addClassName("admin-view");
		setSizeFull();
		getStyle().set("border", "2px solid Grey");
		getStyle().set("border-radius", "10px");
		configureGrid();
		configureForm(roleService, branchService);

		add(getToolbar(), getContent());
		updateList();
		closeEditor();
	}

	private Component getContent() {
		content = new SplitLayout(grid, form);
//      VerticalLayout content = new VerticalLayout(grid, form);

//      content.setFlexGrow(2, grid); 
//      content.setFlexGrow(1, form);
		content.setOrientation(SplitLayout.Orientation.VERTICAL);
		content.setSplitterPosition(100);
		content.addClassNames("content");
		content.setSizeFull();
		return content;
	}

	private void configureForm(RoleService roleService, BranchService branchService) {
		form = new UserForm(roleService.findAll(null), branchService.findAllBranches(null));
//        form.setWidth("25em");
		form.addListener(UserForm.SaveEvent.class, this::saveUser);
		form.addListener(UserForm.DeleteEvent.class, this::deleteContact);
		form.addListener(UserForm.CloseEvent.class, e -> closeEditor());
	}

	private void saveUser(UserForm.SaveEvent event) {
		service.save(event.getUser());
		updateList();
		closeEditor();
	}

	private void deleteContact(UserForm.DeleteEvent event) {
		service.delete(event.getUser());
		updateList();
		closeEditor();
	}

	private void configureGrid() {
		grid.addClassNames("user-grid");
		grid.setSizeFull();
		grid.getStyle().set("border", "2px solid Grey");
		grid.getStyle().set("border-radius", "10px");
		grid.setColumns("userId", "userName", "branchName", "status");
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addItemClickListener(event -> editUser(event.getItem()));
		grid.setPageSize(8);
		grid.setPaginatorSize(5);
//		grid.asSingleSelect().addValueChangeListener(event -> editUser(event.getValue()));
	}

	private VerticalLayout getToolbar() {
		filterText.setPlaceholder("Filter by name...");
		filterText.setClearButtonVisible(true);
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
//		filterText.addValueChangeListener(e -> updateList());
		branchCB = new ComboBox<Branch>("Branch");
		branchCB.setWidth(100, Unit.PERCENTAGE);
		branchCB.setItems(branchService.findAllBranches(null));
		branchCB.setItemLabelGenerator(Branch::getBranchName);
		Button addContactButton = new Button("Add User");
		addContactButton.addClickListener(click -> addContact());
		
		Button searchUsers = new Button("Search");
		searchUsers.addClickListener(click -> updateList());
		Button deleteUsers = new Button("Delete User");
		deleteUsers.addClickListener(click -> deleteUsers());
		VerticalLayout toolbar = new VerticalLayout();
		HorizontalLayout row1 = new HorizontalLayout(filterText,branchCB);
//		toolbar.setWidth(100, Unit.PERCENTAGE);
		HorizontalLayout row2 = new HorizontalLayout(addContactButton,searchUsers,deleteUsers);
//		toolbar.setWidth(100, Unit.PERCENTAGE);
		toolbar.add(row1,row2);
		toolbar.addClassName("toolbar");
		return toolbar;
	}

	public void editUser(User user) {
		if (user == null) {
			closeEditor();
		} else {
			user.setPassword(null);
			form.setUser(user);
			form.setVisible(true);
			content.setSplitterPosition(20);
			addClassName("editing");
		}
	}

	private void closeEditor() {
		form.setUser(null);
		form.setVisible(false);
		removeClassName("editing");
	}

	private void addContact() {
//		grid.asSingleSelect().clear();
		editUser(new User());
	}

	private void updateList() {
		Branch branch = branchCB.getValue();
		if(null != filterText.getValue() && null != branch)
			grid.setItems(service.findAllUsers(filterText.getValue(),branch.getBranchCode()));
		else if(null != filterText.getValue())
			grid.setItems(service.findAllUsers(filterText.getValue(),null));
		else if(null != branch) {
			System.out.println("Executing branch filter in UI ");
			grid.setItems(service.findAllUsers(null,branch.getBranchCode()));
		}else
			grid.setItems(service.findAllUsers(null,null));
		closeEditor();
		content.setSplitterPosition(100);
	}
	
	private void deleteUsers() {
		Set<User> selectedUsers = grid.getSelectedItems();
		if(null != selectedUsers && !selectedUsers.isEmpty()) {
			ConfirmDialog dialog = new ConfirmDialog();
			dialog.setHeader("Delete Users");
			dialog.setText("Are you sure you want to delete User's ?");

			dialog.setCancelable(true);

			dialog.setConfirmText("Delete");
			dialog.addConfirmListener(event -> finalDelete(selectedUsers));
			dialog.open();
		}else
			Notification.show("No records selected for delete", 5000, Notification.Position.MIDDLE);
	}
	
	private void finalDelete(Set<User> selectedUsers) {
//		service.
	}
}

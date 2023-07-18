package com.aakhya.smartcall.application.security.ui;

import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.security.entity.Role;
import com.aakhya.smartcall.application.security.service.RoleService;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value="role",layout = SecurityMainLayout.class)
@PermitAll
public class UserRoleView extends VerticalLayout { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 6709559976014838630L;
	PaginatedGrid<Role,?> grid = new PaginatedGrid<>(Role.class); 
    TextField filterText = new TextField();
    UserRoleForm form;
    RoleService service;
    SplitLayout content;
    Label noOfRecords;

    public UserRoleView(RoleService service) {
    	this.service = service;
        addClassName("list-view");
        setSizeFull();
        getStyle().set("border", "2px solid Grey");
		getStyle().set("border-radius", "10px");
        configureGrid(); 
        configureForm();

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

    private void configureForm() {
        form = new UserRoleForm(); 
//        form.setWidth("25em");
        form.addListener(UserRoleForm.SaveEvent.class, this::saveContact); 
        form.addListener(UserRoleForm.DeleteEvent.class, this::deleteContact); 
        form.addListener(UserRoleForm.CloseEvent.class, e -> closeEditor());
    }
    
    private void saveContact(UserRoleForm.SaveEvent event) {
        service.save(event.getRole());
        updateList();
        closeEditor();
    }

    private void deleteContact(UserRoleForm.DeleteEvent event) {
        service.delete(event.getRole());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.getStyle().set( "border" , "2px solid Grey" ) ; 
        grid.getStyle().set("border-radius", "10px");
        grid.setColumns("description", "validFrom","validTo"); 
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.addItemClickListener(event ->
        editRole(event.getItem())); 
        grid.setPageSize(10);
        grid.setPaginatorSize(5);
    }

    private HorizontalLayout getToolbar() {
    	noOfRecords = new Label();
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); 
        filterText.addValueChangeListener(e -> updateList());
        

        Button addContactButton = new Button("Add Role");
        addContactButton.addClickListener(click -> addRole());
        
        Button deleteRoles = new Button("Delete Role");
        deleteRoles.addClickListener(click -> deleteRoles());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton,deleteRoles,noOfRecords); 
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    
    public void deleteRoles() {
    	Set<Role> selectedRoles = grid.getSelectedItems();
    	if(null != selectedRoles && !selectedRoles.isEmpty()) {
    		ConfirmDialog dialog = new ConfirmDialog();
    		dialog.setHeader("Delete Role");
    		dialog.setText("Are you sure you want to delete Role?");

    		dialog.setCancelable(true);

    		dialog.setConfirmText("Delete");
    		dialog.addConfirmListener(event -> delete(selectedRoles));
    		dialog.open();
    	}else {
    		MessageUtils.validationMessage("No record selected");
    	}
    }
    
    public void delete(Set<Role> selectedRoles) {
    	service.deleteRoles(selectedRoles);
    	MessageUtils.successMessage("Roles deleted successfully!!!");
    }
    
    public void editRole(Role role) { 
        if (role == null) {
            closeEditor();
        } else {
            form.setRole(role);
            form.setVisible(true);
            addClassName("editing");
            content.setSplitterPosition(30);
        }
    }

    private void closeEditor() {
        form.setRole(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addRole() { 
        grid.asSingleSelect().clear();
        editRole(new Role());
    }
    
    private void updateList() { 
    	List<Role> roles = service.findAll(filterText.getValue());
        grid.setItems(roles);
        noOfRecords.setText("No of records fetched is : "+roles.size());
        content.setSplitterPosition(100);
    }
}
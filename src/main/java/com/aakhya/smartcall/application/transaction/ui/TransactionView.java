package com.aakhya.smartcall.application.transaction.ui;


import javax.annotation.security.PermitAll;

import com.aakhya.smartcall.application.security.entity.Role;
import com.aakhya.smartcall.application.security.service.RoleService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value="transactionView",layout = TransactionMainLayout.class)
@PermitAll
public class TransactionView extends VerticalLayout { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 6709559976014838630L;
	Grid<Role> grid = new Grid<>(Role.class); 
    TextField filterText = new TextField();
    TransactionForm form;
    RoleService service;
    SplitLayout content;

    public TransactionView(RoleService service) {
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
  	content.setSplitterPosition(20);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new TransactionForm(); 
//        form.setWidth("25em");
        form.addListener(TransactionForm.SaveEvent.class, this::saveContact); 
        form.addListener(TransactionForm.DeleteEvent.class, this::deleteContact); 
        form.addListener(TransactionForm.CloseEvent.class, e -> closeEditor());
    }
    
    private void saveContact(TransactionForm.SaveEvent event) {
        service.save(event.getRole());
        updateList();
        closeEditor();
    }

    private void deleteContact(TransactionForm.DeleteEvent event) {
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
//        grid.addComponentColumn(transactionDataSet -> {
//        	Button viewData = new Button("View");
//        	viewData.addClickListener(null);
//        	return viewData;
//        });
        
        
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
        grid.asSingleSelect().addValueChangeListener(event ->
        editRole(event.getValue())); 
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); 
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add Role");
        addContactButton.addClickListener(click -> addRole());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton); 
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    
    public void editRole(Role role) { 
        if (role == null) {
            closeEditor();
        } else {
            form.setRole(role);
            form.setVisible(true);
            addClassName("editing");
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
        grid.setItems(service.findAll(filterText.getValue()));
    }
}
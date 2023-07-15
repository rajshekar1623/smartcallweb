package com.aakhya.smartcall.application.admin.ui;


import javax.annotation.security.PermitAll;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.GenericKey;
import com.aakhya.smartcall.application.admin.service.GenericKeyService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value="genericKey",layout = AdminMainLayout.class)
@PermitAll
public class GenericKeyView extends VerticalLayout { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 6709559976014838630L;
	PaginatedGrid<GenericKey,?> grid = new PaginatedGrid<>(GenericKey.class); 
    TextField filterText = new TextField();
    GenericKeyForm form;
    GenericKeyService service;
    SplitLayout content;

    public GenericKeyView(GenericKeyService service) {
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
        form = new GenericKeyForm(); 
//        form.setWidth("25em");
        form.addListener(GenericKeyForm.SaveEvent.class, this::saveContact); 
        form.addListener(GenericKeyForm.DeleteEvent.class, this::deleteContact); 
        form.addListener(GenericKeyForm.CloseEvent.class, e -> closeEditor());
    }
    
    private void saveContact(GenericKeyForm.SaveEvent event) {
        service.saveGenericKey(event.getGenericKey());
        updateList();
        closeEditor();
    }

    private void deleteContact(GenericKeyForm.DeleteEvent event) {
        service.deleteGenericKey(event.getGenericKey());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.getStyle().set( "border" , "2px solid Grey" ) ; 
        grid.getStyle().set("border-radius", "10px");
        grid.setColumns("genericKey", "description", "validFrom","validTo"); 
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.addItemClickListener(event ->
        editGenericKey(event.getItem())); 
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); 
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add Generic Key");
        addContactButton.addClickListener(click -> addGenericKey());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton); 
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    
    public void editGenericKey(GenericKey genericKey) { 
        if (genericKey == null) {
            closeEditor();
        } else {
            form.setGenericKey(genericKey);
            form.setVisible(true);
            addClassName("editing");
            content.setSplitterPosition(30);
        }
    }

    private void closeEditor() {
        form.setGenericKey(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addGenericKey() { 
        grid.asSingleSelect().clear();
        editGenericKey(new GenericKey());
    }
    
    private void updateList() { 
        grid.setItems(service.findAllGenericKeys(filterText.getValue()));
        form.setGenericKey(null);
        form.setVisible(false);
        removeClassName("editing");
        content.setSplitterPosition(100);
    }
}
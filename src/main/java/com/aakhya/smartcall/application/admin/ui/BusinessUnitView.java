package com.aakhya.smartcall.application.admin.ui;


import javax.annotation.security.PermitAll;

import com.aakhya.smartcall.application.admin.entity.BusinessUnit;
import com.aakhya.smartcall.application.admin.service.BusinessUnitService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value="businessUnit",layout = AdminMainLayout.class)
@PermitAll
public class BusinessUnitView extends VerticalLayout { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 6709559976014838630L;
	Grid<BusinessUnit> grid = new Grid<>(BusinessUnit.class); 
    TextField filterText = new TextField();
    BusinessUnitForm form;
    BusinessUnitService service;
    SplitLayout content;

    public BusinessUnitView(BusinessUnitService service) {
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
        form = new BusinessUnitForm(); 
//        form.setWidth("25em");
        form.addListener(BusinessUnitForm.SaveEvent.class, this::saveContact); 
        form.addListener(BusinessUnitForm.DeleteEvent.class, this::deleteContact); 
        form.addListener(BusinessUnitForm.CloseEvent.class, e -> closeEditor());
    }
    
    private void saveContact(BusinessUnitForm.SaveEvent event) {
        service.saveBusinessUnit(event.getBusinessUnit());
        updateList();
        closeEditor();
    }

    private void deleteContact(BusinessUnitForm.DeleteEvent event) {
        service.deleteBusinessUnit(event.getBusinessUnit());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.getStyle().set( "border" , "2px solid Grey" ) ; 
        grid.getStyle().set("border-radius", "10px");
        grid.setColumns("businessUnitName", "effectiveStartDate","effectiveEndDate"); 
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
        grid.asSingleSelect().addValueChangeListener(event ->
        editBusinessUnit(event.getValue())); 
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); 
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add Business Unit");
        addContactButton.addClickListener(click -> addBusinessUnit());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton); 
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    
    public void editBusinessUnit(BusinessUnit businessUnit) { 
        if (businessUnit == null) {
            closeEditor();
        } else {
            form.setBusinessUnit(businessUnit);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setBusinessUnit(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addBusinessUnit() { 
        grid.asSingleSelect().clear();
        editBusinessUnit(new BusinessUnit());
    }
    
    private void updateList() { 
        grid.setItems(service.findAllBusinessUnits(filterText.getValue()));
    }
}
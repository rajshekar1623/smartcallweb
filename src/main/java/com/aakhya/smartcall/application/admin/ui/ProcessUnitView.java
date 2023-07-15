package com.aakhya.smartcall.application.admin.ui;


import javax.annotation.security.PermitAll;
import com.aakhya.smartcall.application.admin.entity.ProcessUnit;
import com.aakhya.smartcall.application.admin.service.ProcessUnitService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value="processUnit",layout = AdminMainLayout.class)
@PermitAll
public class ProcessUnitView extends VerticalLayout { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 6709559976014838630L;
	Grid<ProcessUnit> grid = new Grid<>(ProcessUnit.class); 
    TextField filterText = new TextField();
    ProcessUnitForm form;
    ProcessUnitService service;
    SplitLayout content;

    public ProcessUnitView(ProcessUnitService service) {
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
        form = new ProcessUnitForm(); 
//        form.setWidth("25em");
        form.addListener(ProcessUnitForm.SaveEvent.class, this::saveContact); 
        form.addListener(ProcessUnitForm.DeleteEvent.class, this::deleteContact); 
        form.addListener(ProcessUnitForm.CloseEvent.class, e -> closeEditor());
    }
    
    private void saveContact(ProcessUnitForm.SaveEvent event) {
        service.saveProcessUnit(event.getProcessUnit());
        updateList();
        closeEditor();
    }

    private void deleteContact(ProcessUnitForm.DeleteEvent event) {
        service.deleteProcessUnit(event.getProcessUnit());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.getStyle().set( "border" , "2px solid Grey" ) ; 
        grid.getStyle().set("border-radius", "10px");
        grid.setColumns("processingUnitName", "effectiveStartDate","effectiveEndDate"); 
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
        grid.asSingleSelect().addValueChangeListener(event ->
        editProcessUnit(event.getValue())); 
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); 
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add Processing Unit");
        addContactButton.addClickListener(click -> addProcessUnit());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton); 
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    
    public void editProcessUnit(ProcessUnit processUnit) { 
        if (processUnit == null) {
            closeEditor();
        } else {
            form.setProcessUnit(processUnit);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setProcessUnit(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addProcessUnit() { 
        grid.asSingleSelect().clear();
        editProcessUnit(new ProcessUnit());
       
    }
    
    private void updateList() { 
        grid.setItems(service.findAllProcessUnits(filterText.getValue()));
    }
}
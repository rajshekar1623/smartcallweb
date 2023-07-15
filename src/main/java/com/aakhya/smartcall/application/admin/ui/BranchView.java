package com.aakhya.smartcall.application.admin.ui;

import javax.annotation.security.PermitAll;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.entity.GenericKeyType;
import com.aakhya.smartcall.application.admin.service.BranchService;
import com.aakhya.smartcall.application.admin.service.GenericClassifierService;
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

@Route(value="admin",layout = AdminMainLayout.class)
@PermitAll
public class BranchView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2474642139276717277L;
	PaginatedGrid<Branch,?> grid = new PaginatedGrid<>(Branch.class); 
    TextField filterText = new TextField();
    BranchForm form;
    BranchService service;
    GenericClassifierService classifierService;
    SplitLayout content;
    
    public BranchView(BranchService service,GenericClassifierService classifierService) {
    	this.service = service;
    	this.classifierService = classifierService;
    	addClassName("admin-view");
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
		form = new BranchForm(classifierService.findByGenericKey(GenericKeyType.BRANCH_CAT.getValue()),
				classifierService.findByGenericKey(GenericKeyType.BRANCH_TYPE.getValue()),
				service.findAllBranches(null)); 
//        form.setWidth("25em");
        form.addListener(BranchForm.SaveEvent.class, this::saveBranch); 
        form.addListener(BranchForm.DeleteEvent.class, this::deleteContact); 
        form.addListener(BranchForm.CloseEvent.class, e -> closeEditor());
    }
    
    private void saveBranch(BranchForm.SaveEvent event) {
        service.saveBranch(event.getBranch());
        updateList();
        closeEditor();
    }

    private void deleteContact(BranchForm.DeleteEvent event) {
        service.deleteBranch(event.getBranch());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("branch-grid");
        grid.setSizeFull();
        grid.getStyle().set( "border" , "2px solid Grey" ) ; 
        grid.getStyle().set("border-radius", "10px");
        grid.setColumns("branchCode", "branchName", "branchEmailId","status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.addItemClickListener(event ->
        editBranch(event.getItem())); 
        grid.setPageSize(10);
        grid.setPaginatorSize(5);
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); 
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add Branch");
        addContactButton.addClickListener(click -> addContact());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton); 
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    
    public void editBranch(Branch branch) { 
        if (branch == null) {
            closeEditor();
        } else {
            form.setBranch(branch);
            form.setVisible(true);
            addClassName("editing");
            content.setSplitterPosition(30);
        }
    }

    private void closeEditor() {
        form.setBranch(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addContact() { 
//        grid.asSingleSelect().clear();
        editBranch(new Branch());
    }
    
    private void updateList() { 
        grid.setItems(service.findAllBranches(filterText.getValue()));
        form.setBranch(null);
        form.setVisible(false);
        removeClassName("editing");
        content.setSplitterPosition(100);
    }
}

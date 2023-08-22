package com.aakhya.smartcall.application.admin.ui;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.entity.GenericKeyType;
import com.aakhya.smartcall.application.admin.service.BranchService;
import com.aakhya.smartcall.application.admin.service.GenericClassifierService;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route(value = "admin", layout = AdminMainLayout.class)
@PermitAll
public class BranchView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2474642139276717277L;
	PaginatedGrid<Branch, ?> grid = new PaginatedGrid<>(Branch.class);
	TextField filterText = new TextField();
	TextField branchCode = new TextField();
	Label noOfRecordsFetched = new Label();
	BranchForm form;
	BranchService service;
	GenericClassifierService classifierService;
	SplitLayout content;

	public BranchView(BranchService service, GenericClassifierService classifierService) {
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
				service.findAllBranches(null, null));
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
		grid.getStyle().set("border", "2px solid Grey");
		grid.getStyle().set("border-radius", "10px");
		grid.setColumns("branchCode", "branchName", "branchEmailId", "status");
		grid.addColumn(new ComponentRenderer<>(branch -> {
			Button viewDetails = new Button("Location");
			viewDetails.addClickListener(e -> editBranchLocation(branch));
			return viewDetails;
		}));
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addItemClickListener(event -> editBranch(event.getItem()));
		grid.setPageSize(10);
		grid.setPaginatorSize(5);
	}

	private void editBranchLocation(Branch branch) {
		Dialog dialog = new Dialog();
		dialog.setHeaderTitle("Capture Location for Branch:: "+branch.getBranchName());
		dialog.setHeight("600px");
		dialog.setWidth("1000px");
		BranchLocationView branchLocationView = new BranchLocationView(branch,service);
		Button cancelButton = new Button("Close", e -> dialog.close());
		Button saveButton = new Button("Save", e -> branchLocationView.saveBranch(dialog));
		dialog.add(branchLocationView);
		dialog.getFooter().add(saveButton,cancelButton);
		dialog.open();
	}

//	private void saveBranchLocation(Branch branch,Dialog dialog) {
////		service.saveBranch(branch);
//		dialog.close();
//		service.getDistance(null, null, null);
//		MessageUtils.successMessage("Successfully location for Branch :: "+branch.getBranchName());
//	}

	private HorizontalLayout getToolbar() {
		filterText.setPlaceholder("Filter by name...");
		filterText.setClearButtonVisible(true);
//        filterText.setValueChangeMode(ValueChangeMode.LAZY); 
//        filterText.addValueChangeListener(e -> updateList());

		branchCode.setPlaceholder("Filter by branch code");
		branchCode.setClearButtonVisible(true);
//        branchCode.setValueChangeMode(ValueChangeMode.LAZY); 
//        branchCode.addValueChangeListener(e -> updateList());

		Button search = new Button("Search");
		search.addClickListener(click -> updateList());
		Button addBranch = new Button("Add Branch");
		addBranch.addClickListener(click -> addContact());
		Button reset = new Button("Reset");
		reset.addClickListener(click -> reset());
		Button delete = new Button("Delete");
		delete.addClickListener(click -> deleteBranches());

		HorizontalLayout toolbar = new HorizontalLayout(filterText, branchCode, search, addBranch, reset,
				noOfRecordsFetched);
		toolbar.setVerticalComponentAlignment(Alignment.CENTER, noOfRecordsFetched);
		toolbar.addClassName("toolbar");
		return toolbar;
	}

	private void deleteBranches() {
		Set<Branch> branchesToDelete = grid.getSelectedItems();
		if (null != branchesToDelete && !branchesToDelete.isEmpty()) {
			ConfirmDialog dialog = new ConfirmDialog();
			dialog.setHeader("Delete ");
			dialog.setText("Are you sure you want to delete Branches?");
			dialog.setCancelable(true);
			dialog.setConfirmText("Delete");
			dialog.addConfirmListener(event -> finalDelete(branchesToDelete));
			dialog.open();
		} else {
			MessageUtils.validationMessage("No records seleted");
		}
	}

	private void finalDelete(Set<Branch> branchesToDelete) {
		service.deleteBranches(branchesToDelete);
		MessageUtils.successMessage("Successfully deleted " + branchesToDelete.size() + " branches");
	}

	private void reset() {
		filterText.setValue("");
		branchCode.setValue("");
		grid.setItems(new ArrayList<Branch>());
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
		List<Branch> branches = service.findAllBranches(filterText.getValue(), branchCode.getValue());
		grid.setItems(branches);
		noOfRecordsFetched.setText("No of branches fetched is : " + branches.size());
		noOfRecordsFetched.getStyle().set("color", "red");
		form.setBranch(null);
		form.setVisible(false);
		removeClassName("editing");
		content.setSplitterPosition(100);
	}
}

package com.aakhya.smartcall.application.admin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.GenericKey;
import com.aakhya.smartcall.application.admin.service.GenericKeyService;
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
import com.vaadin.flow.router.Route;

@Route(value = "genericKey", layout = AdminMainLayout.class)
@PermitAll
public class GenericKeyView extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6709559976014838630L;
	PaginatedGrid<GenericKey, ?> grid = new PaginatedGrid<>(GenericKey.class);
	TextField filterText = new TextField();
	TextField genericKeyText = new TextField();
	Label noOfRecordsFetched = new Label();
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
		grid.getStyle().set("border", "2px solid Grey");
		grid.getStyle().set("border-radius", "10px");
		grid.setColumns("genericKey", "description", "validFrom", "validTo");
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addItemClickListener(event -> editGenericKey(event.getItem()));
	}

	private HorizontalLayout getToolbar() {
		filterText.setPlaceholder("Filter by description...");
		filterText.setClearButtonVisible(true);

		genericKeyText.setPlaceholder("Filter by Generic Key...");
		genericKeyText.setClearButtonVisible(true);

		Button search = new Button("Search");
		search.addClickListener(click -> updateList());

		Button reset = new Button("Reset");
		reset.addClickListener(click -> reset());

		Button addGenericKey = new Button("Add Generic Key");
		addGenericKey.addClickListener(click -> addGenericKey());

		Button delete = new Button("Delete");
		delete.addClickListener(click -> deleteGenericKeys());

		HorizontalLayout toolbar = new HorizontalLayout(filterText, genericKeyText, 
				search, reset, addGenericKey,
				delete, noOfRecordsFetched);
		toolbar.setVerticalComponentAlignment(Alignment.CENTER, noOfRecordsFetched);
		toolbar.addClassName("toolbar");
		return toolbar;
	}

	private void reset() {
		filterText.setValue("");
		genericKeyText.setValue("");
		grid.setItems(new ArrayList<GenericKey>());
		content.setSplitterPosition(100);
	}

	private void deleteGenericKeys() {
		Set<GenericKey> genericKeysToDelete = grid.getSelectedItems();
		if(null != genericKeysToDelete && !genericKeysToDelete.isEmpty()) {
			ConfirmDialog dialog = new ConfirmDialog();
    		dialog.setHeader("Delete ");
    		dialog.setText("Are you sure you want to delete Branches?");

    		dialog.setCancelable(true);

    		dialog.setConfirmText("Delete");
    		dialog.addConfirmListener(event -> finalDelete(genericKeysToDelete));
    		dialog.open();
		}else {
			MessageUtils.validationMessage("No records seleted");
		}
	}

	private void finalDelete(Set<GenericKey> genericKeysToDelete) {
		service.deleteGenericKeys(genericKeysToDelete);
		MessageUtils.successMessage("Successfully deleted "+genericKeysToDelete.size()+" Generickeys");

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
//        grid.asSingleSelect().clear();
		editGenericKey(new GenericKey());
	}

	private void updateList() {
		List<GenericKey> genericKeys = service.findAllGenericKeys(filterText.getValue(), genericKeyText.getValue());
		grid.setItems(genericKeys);
		noOfRecordsFetched.setText("No of records fetched is : "+genericKeys.size());
		noOfRecordsFetched.getStyle().set("color", "red");
		form.setGenericKey(null);
		form.setVisible(false);
		removeClassName("editing");
		content.setSplitterPosition(100);
	}
}
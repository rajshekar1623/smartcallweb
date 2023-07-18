package com.aakhya.smartcall.application.admin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.admin.entity.GenericKey;
import com.aakhya.smartcall.application.admin.service.GenericClassifierService;
import com.aakhya.smartcall.application.admin.service.GenericKeyService;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PermitAll
@Route(value = "genericClassifier", layout = AdminMainLayout.class)
@PageTitle("GenericClassifiers | Vaadin CRM")
public class GenericClassifierView extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5072433789129699688L;
	PaginatedGrid<GenericClassifier, ?> grid = new PaginatedGrid<>(GenericClassifier.class);
	TextField filterText = new TextField();
	ComboBox<GenericKey> genericKeyCB = new ComboBox<GenericKey>();
	Label noOfRecordsFetched = new Label();
	GenericClassifierForm form;
	GenericKeyService genericKeyService;
	GenericClassifierService genericClassifierService;
	SplitLayout content;

	public GenericClassifierView(GenericKeyService genericKeyService,
			GenericClassifierService genericClassifierService) {
		this.genericKeyService = genericKeyService;
		this.genericClassifierService = genericClassifierService;
		addClassName("GenericClassifier-view");
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
//        VerticalLayout content = new VerticalLayout(grid, form);

//        content.setFlexGrow(2, grid); 
//        content.setFlexGrow(1, form);
		content.setOrientation(SplitLayout.Orientation.VERTICAL);
		content.setSplitterPosition(100);
		content.addClassNames("content");
		content.setSizeFull();
		return content;
	}

	private void configureForm() {
		form = new GenericClassifierForm(genericKeyService.findAllGenericKeys(null, null),
				genericClassifierService.findAllClassifiers(null, null));
//        form.setWidth("25em");
		form.addListener(GenericClassifierForm.SaveEvent.class, this::saveGenericClassifier);
		form.addListener(GenericClassifierForm.DeleteEvent.class, this::deleteGenericClassifier);
		form.addListener(GenericClassifierForm.CloseEvent.class, e -> closeEditor());
	}

	private void saveGenericClassifier(GenericClassifierForm.SaveEvent event) {
		genericClassifierService.saveGenericClassifier(event.getGenericClassifier());
		updateList();
		closeEditor();
	}

	private void deleteGenericClassifier(GenericClassifierForm.DeleteEvent event) {
		genericClassifierService.deleteGenericClassifier(event.getGenericClassifier());
		updateList();
		closeEditor();
	}

	private void configureGrid() {
		grid.addClassNames("genericClassifier-grid");
		grid.setSizeFull();
		grid.getStyle().set("border", "2px solid Grey");
		grid.getStyle().set("border-radius", "10px");
		grid.setColumns("description", "genericKeyDescription", "parentKeyStr");
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addItemClickListener(event -> editGenericClassifier(event.getItem()));
	}

	private HorizontalLayout getToolbar() {
		filterText.setPlaceholder("Filter by name...");
		filterText.setClearButtonVisible(true);
		genericKeyCB.setPlaceholder("Filter by generic key...");
		genericKeyCB.setItems(genericKeyService.findAllGenericKeys(null, null));
		genericKeyCB.setItemLabelGenerator(GenericKey::getDescription);
		Button search = new Button("Search");
		search.addClickListener(click -> updateList());
		Button addGenericClassifierButton = new Button("Add Generic Classifier");
		addGenericClassifierButton.addClickListener(click -> addGenericClassifier());
		Button reset = new Button("Reset");
		reset.addClickListener(click -> reset());
		Button delete = new Button("Delete");
		delete.addClickListener(click -> deleteClassifiers());

		HorizontalLayout toolbar = new HorizontalLayout(filterText,genericKeyCB,search,
				addGenericClassifierButton,reset,delete,noOfRecordsFetched);
		toolbar.setVerticalComponentAlignment(Alignment.CENTER, noOfRecordsFetched);
		toolbar.addClassName("toolbar");
		return toolbar;
	}
	
	private void reset() {
		filterText.setValue("");
		genericKeyCB.setValue(null);
		grid.setItems(new ArrayList<GenericClassifier>());
	}
	
	public void deleteClassifiers() {
		Set<GenericClassifier> classifiersToDelete = grid.getSelectedItems();
		if (null != classifiersToDelete && !classifiersToDelete.isEmpty()) {
			ConfirmDialog dialog = new ConfirmDialog();
			dialog.setHeader("Delete ");
			dialog.setText("Are you sure you want to delete Classifiers?");
			dialog.setCancelable(true);
			dialog.setConfirmText("Delete");
			dialog.addConfirmListener(event -> finalDelete(classifiersToDelete));
			dialog.open();
		} else {
			MessageUtils.validationMessage("No records seleted");
		}
	}
	
	private void finalDelete(Set<GenericClassifier> classifiersToDelete) {
		genericClassifierService.deleteGenericClassifiers(classifiersToDelete);
		MessageUtils.successMessage("Successfully deleted " + classifiersToDelete.size() + " Classifiers");
	}

	public void editGenericClassifier(GenericClassifier genericClassifier) {
		if (genericClassifier == null) {
			closeEditor();
		} else {
			form.setGenericClassifier(genericClassifier);
			form.setVisible(true);
			addClassName("editing");
			content.setSplitterPosition(20);
		}
	}

	private void closeEditor() {
		form.setGenericClassifier(null);
		form.setVisible(false);
		removeClassName("editing");
		content.setSplitterPosition(100);
	}

	private void addGenericClassifier() {
//        grid.asSingleSelect().clear();
		editGenericClassifier(new GenericClassifier());
	}

	private void updateList() {
		GenericKey selectedGenericKey = genericKeyCB.getValue();
		if (null != selectedGenericKey) {
			List<GenericClassifier> classifiers = genericClassifierService.findAllClassifiers(filterText.getValue(),
					selectedGenericKey.getGenericKey());
			grid.setItems(classifiers);
			noOfRecordsFetched.setText("No of branches fetched is : " + classifiers.size());
			noOfRecordsFetched.getStyle().set("color", "red");
		}else {
			List<GenericClassifier> classifiers = genericClassifierService.findAllClassifiers(filterText.getValue(), 
					null);
			grid.setItems(classifiers);
			noOfRecordsFetched.setText("No of branches fetched is : " + classifiers.size());
			noOfRecordsFetched.getStyle().set("color", "red");
		}
		closeEditor();
	}
}
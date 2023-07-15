package com.aakhya.smartcall.application.product.ui;

import java.util.Set;

import javax.annotation.security.PermitAll;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.GenericKeyType;
import com.aakhya.smartcall.application.admin.service.GenericClassifierService;
import com.aakhya.smartcall.application.admin.ui.AdminMainLayout;
import com.aakhya.smartcall.application.product.entity.Product;
import com.aakhya.smartcall.application.product.service.ProductService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = "product", layout = AdminMainLayout.class)
@PermitAll
public class ProductView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2474642139276717277L;
	PaginatedGrid<Product, ?> grid = new PaginatedGrid<>(Product.class);
	TextField filterText = new TextField();
	ProductForm form;
	ProductService service;
	GenericClassifierService classifierService;
	SplitLayout content;

	public ProductView(ProductService service, GenericClassifierService classifierService) {
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
		form = new ProductForm(classifierService.findByGenericKey(GenericKeyType.BRANCH_TYPE.getValue()),
				service.findAll(null));
//        form.setWidth("25em");
		form.addListener(ProductForm.SaveEvent.class, this::saveProduct);
		form.addListener(ProductForm.DeleteEvent.class, this::deleteContact);
		form.addListener(ProductForm.CloseEvent.class, e -> closeEditor());
	}

	private void saveProduct(ProductForm.SaveEvent event) {
		service.save(event.getProduct());
		updateList();
		closeEditor();
	}

	private void deleteContact(ProductForm.DeleteEvent event) {
		service.delete(event.getProduct());
		updateList();
		closeEditor();
	}

	private void configureGrid() {
		grid.addClassNames("branch-grid");
		grid.setSizeFull();
		grid.getStyle().set("border", "2px solid Grey");
		grid.getStyle().set("border-radius", "10px");
		grid.setColumns("productCode", "productName", "validFrom", "validTo", "status");
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addItemClickListener(event -> editProduct(event.getItem()));
		grid.setPageSize(8);
		grid.setPaginatorSize(5);
	}

	private HorizontalLayout getToolbar() {
		filterText.setPlaceholder("Filter by name...");
		filterText.setClearButtonVisible(true);
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
		
		Button search = new Button("Search");
		search.addClickListener(click -> updateList());
		Button addContactButton = new Button("Add Product");
		addContactButton.addClickListener(click -> addContact());
		Button deleteProducts = new Button("Delete");
		deleteProducts.addClickListener(click -> deleteProducts());

		HorizontalLayout toolbar = new HorizontalLayout(filterText, search,addContactButton,deleteProducts);
		toolbar.addClassName("toolbar");
		return toolbar;
	}

	private void deleteProducts() {
		Set<Product> products = grid.getSelectedItems();
		if(null !=products && !products.isEmpty()) {
			ConfirmDialog dialog = new ConfirmDialog();
			dialog.setHeader("Delete Users");
			dialog.setText("Are you sure you want to delete Product ?");
			dialog.setCancelable(true);
			dialog.setConfirmText("Delete");
			dialog.addConfirmListener(event -> finalDelete(products));
			dialog.open();
		}else
			Notification.show("No records selected for delete", 5000, Notification.Position.MIDDLE);
	}
	
	public void finalDelete(Set<Product> products) {
		service.deleteProducts(products);
	}

	public void editProduct(Product branch) {
		if (branch == null) {
			closeEditor();
		} else {
			content.setSplitterPosition(20);
			form.setProduct(branch);
			form.setVisible(true);
			addClassName("editing");
		}
	}

	private void closeEditor() {
		form.setProduct(null);
		form.setVisible(false);
		removeClassName("editing");
	}

	private void addContact() {
		grid.asSingleSelect().clear();
		editProduct(new Product());
	}

	private void updateList() {
		grid.setItems(service.findAll(filterText.getValue()));
		content.setSplitterPosition(100);
	}
}

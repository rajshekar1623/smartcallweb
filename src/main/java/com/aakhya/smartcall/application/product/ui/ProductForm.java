package com.aakhya.smartcall.application.product.ui;

import java.util.List;

import com.aakhya.smartcall.application.SmartCallWebForm;
import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.product.entity.Product;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
//import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class ProductForm extends SmartCallWebForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30588516090628260L;
	private static final String objectName = "Product";
	TextField productCode = new TextField("Product Code");
	TextField productName = new TextField("ProductName");
	TextField maxTenure = new TextField("Max Tenure");
	TextField minTenure = new TextField("Min Tenure");
	TextField maxLoanAmount = new TextField("Max Limit");
	TextField minLoanAmount = new TextField("Min Limit");
//	ComboBox<GenericClassifier> productTypeDesc = new ComboBox<>("Product Type");
//	ComboBox<Product> parentProductObject = new ComboBox<>("Parent Product");
//	TextField productAddress = new TextField("Adress");
//	TextField productPincode = new TextField("Pincode");

	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	Binder<Product> binder = new BeanValidationBinder<>(Product.class);
	private Product product;

	public ProductForm(List<GenericClassifier> productTypes, List<Product> parentProductes) {

		addClassName("product-form");
		getStyle().set( "border" , "2px solid Grey" ) ; 
        getStyle().set("border-radius", "10px");
        getStyle().set("padding", "20px");
		binder.bindInstanceFields(this);
//		productTypeDesc.setItems(productTypes);
//		productTypeDesc.setItemLabelGenerator(GenericClassifier::getDescription);
//		parentProductObject.setItems(parentProductes);
//		parentProductObject.setItemLabelGenerator(Product::getProductName);

		add(productCode, productName, maxTenure, minTenure, 
				maxLoanAmount,minLoanAmount,createButtonsLayout());
	}

	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);

		save.addClickListener(event -> validateAndSave());
		delete.addClickListener(event -> MessageUtils.confirmDeleteMessageBox(this, objectName));
		close.addClickListener(event -> fireEvent(new CloseEvent(this)));

		binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
		return new HorizontalLayout(save, delete, close);
	}

	private void validateAndSave() {
		try {
			binder.writeBean(product);
			MessageUtils.confirmSaveMessageBox(this, objectName);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			fireEvent(new SaveEvent(this, product));
			MessageUtils.showSaveNotification(objectName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			fireEvent(new DeleteEvent(this, product));
			MessageUtils.showDeleteNotification(objectName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setProduct(Product product) {
		this.product = product;
		binder.readBean(product);
	}

//Events
	public static abstract class ProductFormEvent extends ComponentEvent<ProductForm> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6082433833350699897L;
		private Product product;

		protected ProductFormEvent(ProductForm source, Product product) {
			super(source, false);
			this.product = product;
		}

		public Product getProduct() {
			return product;
		}
	}

	public static class SaveEvent extends ProductFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4405498981813599396L;

		SaveEvent(ProductForm source, Product product) {
			super(source, product);
		}
	}

	public static class DeleteEvent extends ProductFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8418559976585902363L;

		DeleteEvent(ProductForm source, Product product) {
			super(source, product);
		}

	}

	public static class CloseEvent extends ProductFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6300891543447052361L;

		CloseEvent(ProductForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
	
}
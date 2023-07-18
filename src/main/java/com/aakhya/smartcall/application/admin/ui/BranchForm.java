package com.aakhya.smartcall.application.admin.ui;

import java.util.List;

import com.aakhya.smartcall.application.SmartCallWebForm;
import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class BranchForm extends SmartCallWebForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30588516090628260L;
	TextField branchCode = new TextField("Branch Code");
	TextField branchName = new TextField("BranchName");
	EmailField branchEmailId = new EmailField("Email");
	ComboBox<GenericClassifier> branchCategoryDesc = new ComboBox<>("Branch Category");
	ComboBox<GenericClassifier> branchTypeDesc = new ComboBox<>("Branch Type");
	ComboBox<Branch> parentBranchObject = new ComboBox<>("Parent Branch");
	TextField branchAddress = new TextField("Adress");
	TextField branchPincode = new TextField("Pincode");

	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	Binder<Branch> binder = new BeanValidationBinder<>(Branch.class);
	private Branch branch;

	public BranchForm(List<GenericClassifier> branchCategories,List<GenericClassifier> branchTypes, List<Branch> parentBranches) {

		addClassName("branch-form");
		getStyle().set( "border" , "2px solid Grey" ) ; 
        getStyle().set("border-radius", "10px");
        getStyle().set("padding", "20px");
		binder.bindInstanceFields(this);
		branchCategoryDesc.setItems(branchCategories);
		branchCategoryDesc.setItemLabelGenerator(GenericClassifier::getDescription);
		branchTypeDesc.setItems(branchTypes);
		branchTypeDesc.setItemLabelGenerator(GenericClassifier::getDescription);
		parentBranchObject.setItems(parentBranches);
		parentBranchObject.setItemLabelGenerator(Branch::getBranchName);

		add(branchCode, branchName, branchEmailId, branchCategoryDesc, branchTypeDesc, 
				parentBranchObject,branchAddress,branchPincode, createButtonsLayout());
	}

	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);

		save.addClickListener(event -> validateAndSave());
		delete.addClickListener(event -> MessageUtils.confirmDeleteMessageBox(this, "Branch"));
		close.addClickListener(event -> fireEvent(new CloseEvent(this)));

		binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
		return new HorizontalLayout(save, delete, close);
	}
	
	public void delete() {
		try {
		fireEvent(new DeleteEvent(this, branch));
		MessageUtils.showDeleteNotification("Branch");
		}catch(Exception e) {
			
		}
	}
	private void validateAndSave() {
		try {
			binder.writeBean(branch);
			MessageUtils.confirmSaveMessageBox(this, "Branch");
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			fireEvent(new SaveEvent(this, branch));
			MessageUtils.showSaveNotification("Branch");
		}catch(Exception e) {
			
		}
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
		binder.readBean(branch);
	}

//Events
	public static abstract class BranchFormEvent extends ComponentEvent<BranchForm> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6082433833350699897L;
		private Branch branch;

		protected BranchFormEvent(BranchForm source, Branch branch) {
			super(source, false);
			this.branch = branch;
		}

		public Branch getBranch() {
			return branch;
		}
	}

	public static class SaveEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4405498981813599396L;

		SaveEvent(BranchForm source, Branch branch) {
			super(source, branch);
		}
	}

	public static class DeleteEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8418559976585902363L;

		DeleteEvent(BranchForm source, Branch branch) {
			super(source, branch);
		}

	}

	public static class CloseEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6300891543447052361L;

		CloseEvent(BranchForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
	
}
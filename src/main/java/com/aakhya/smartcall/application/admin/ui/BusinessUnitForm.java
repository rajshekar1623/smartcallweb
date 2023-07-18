package com.aakhya.smartcall.application.admin.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.tatu.TwinColSelect;
import org.vaadin.tatu.TwinColSelect.ColType;
import org.vaadin.tatu.TwinColSelect.FilterMode;

import com.aakhya.smartcall.application.admin.entity.BusinessUnit;
import com.aakhya.smartcall.application.admin.entity.ProcessUnit;
import com.aakhya.smartcall.application.admin.service.ProcessUnitService;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.shared.Registration;

public class BusinessUnitForm extends FormLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30588516090628260L;
	TextField businessUnitName = new TextField("Business Unit Name");
//	TextField description = new TextField("description");
	DatePicker effectiveStartDate = new DatePicker("Valid From");
	DatePicker effectiveEndDate = new DatePicker("Valid Until");
    Label processUnits = new Label("ProcessUnits");
	
	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button close = new Button("Cancel");
	Binder<BusinessUnit> binder = new BeanValidationBinder<>(BusinessUnit.class);
	private BusinessUnit genKey;
	
	@Autowired
	ProcessUnitService processUnitService;

	public BusinessUnitForm() {

		addClassName("businessunit-form");

		getStyle().set("border", "2px solid Grey");
		getStyle().set("border-radius", "10px");
		getStyle().set("padding", "20px");
		binder.bindInstanceFields(this);

		add(businessUnitName, effectiveStartDate, effectiveEndDate,createButtonsLayout(),createTwinColSelect());
		
		
	}

	public List<String> getProcessUnitNamesList() {
		
		 List<ProcessUnit> processUnits = processUnitService.findAllProcessUnits();
	    
	    // create a new list to hold the names of the process units
	    List<String> processUnitNames = new ArrayList<>();
	    
	    // loop through the processUnits list and add the name of each ProcessUnit to the processUnitNames list
	    for (ProcessUnit processUnit : processUnits) {
	        processUnitNames.add(processUnit.getProcessingUnitName());
	    }
		return processUnitNames;
	       
	    }
	

	private VerticalLayout createTwinColSelect() {
		
		VerticalLayout log = new VerticalLayout();
		ListDataProvider<String> list_items_dp = new ListDataProvider<>(Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"));
		
		//Add ProcessUnits Names
		//ListDataProvider<String> list_items_dp = new ListDataProvider<>(getProcessUnitNamesList());
		
		
		// Create a new TwinColSelect
		TwinColSelect<String> select = new TwinColSelect<>();
		select.setItems(list_items_dp);

		// Set comparator for sorting
		list_items_dp.setSortComparator((a, b) -> a.compareTo(b));

		// Use selection listener
		select.addSelectionListener(event -> {
		     log.removeAll();
		     log.addComponentAsFirst(new Span(("Value changed")));
		     event.getValue().forEach(item -> log.addComponentAsFirst(new Span(item + " selected!")));
		 });

		//set ScrollBar for items by setting fixed height
		select.setHeight("250px"); 
	//	select.setWidth("380px");
		

		// Clear checkbox ticks, does not affect the value
		select.clearTicks(ColType.BOTH);

		// Set filtering to data provider
		TextField filterField = new TextField("Filter");
		filterField.addValueChangeListener(event -> {
			list_items_dp.setFilter(item -> item.toUpperCase().startsWith(event.getValue().toUpperCase()));
		});
		// Reset value when filter is changed
		select.setFilterMode(FilterMode.RESETVALUE);
		
		
		return new VerticalLayout(select);
		
	}
	 
	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);

		save.addClickListener(event -> validateAndSave());
		delete.addClickListener(event -> fireEvent(new DeleteEvent(this, genKey)));
		close.addClickListener(event -> fireEvent(new CloseEvent(this)));

		binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
		return new HorizontalLayout(save, delete, close);
	}

	private void validateAndSave() {
		try {
			binder.writeBean(genKey);
			fireEvent(new SaveEvent(this, genKey));
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	public void setBusinessUnit(BusinessUnit genKey) {
		this.genKey = genKey;
		binder.readBean(genKey);
	}

//Events
	public static abstract class BranchFormEvent extends ComponentEvent<BusinessUnitForm> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8256491527189968685L;
		private BusinessUnit genKey;

		protected BranchFormEvent(BusinessUnitForm source, BusinessUnit genKey) {
			super(source, false);
			this.genKey = genKey;
		}

		public BusinessUnit getBusinessUnit() {
			return genKey;
		}
	}

	public static class SaveEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1918003555075373170L;

		SaveEvent(BusinessUnitForm source, BusinessUnit genKey) {
			super(source, genKey);
		}
	}

	public static class DeleteEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = -710845817912666000L;

		DeleteEvent(BusinessUnitForm source, BusinessUnit genKey) {
			super(source, genKey);
		}

	}

	public static class CloseEvent extends BranchFormEvent {
		/**
		 * 
		 */
		private static final long serialVersionUID = -236400317134727310L;

		CloseEvent(BusinessUnitForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}

}
package com.aakhya.smartcall.application.transaction.ui;

import java.util.List;

import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.admin.service.GenericClassifierService;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class PrintDocTemplates extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2654571062473637511L;
	private GenericClassifierService genericClassifierService;
	private Grid<GenericClassifier> templates;
	private List<TransactionDataSet> transactionsDataSets;
	
	PrintDocTemplates(GenericClassifierService genericClassifierService,
			List<TransactionDataSet> transactionsDataSets){
		this.genericClassifierService = genericClassifierService;
		this.transactionsDataSets = transactionsDataSets;
		buildLayout();
	}
	
	private void buildLayout() {
		templates = new Grid<GenericClassifier>(GenericClassifier.class);
		templates.setSizeFull();
		templates.getStyle().set("border", "2px solid Grey");
		templates.getStyle().set("border-radius", "10px");
		templates.setColumns("description");
		templates.addColumn(new ComponentRenderer<>(genericClassifier -> {
			Button printDocument = new Button("Print Document");
			printDocument.addClickListener(e -> printDocument(genericClassifier));
        	return printDocument;
        }));
		add(templates);
	}
	
	private void printDocument(GenericClassifier genericClassifier) {
		
	}
}

package com.aakhya.smartcall.application.transaction.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import software.xdev.vaadin.grid_exporter.GridExporter;

public class DataSetExceptionDetailsView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2467785206636056564L;
	private Grid<DataSetException> exceptionsGrid;
	private String columnName;
	private Map<Long,String> dataSetExceptions;
	private List<DataSetException> exceptions;
	private Button export;

	public DataSetExceptionDetailsView(String columnName,Map<Long, String> firstNameErrorRecords) {
		this.dataSetExceptions = firstNameErrorRecords;
		this.columnName = columnName;
		buildLayout();
	}
	
	private void buildLayout() {
		exceptions = new ArrayList<DataSetException>();
		if(null != dataSetExceptions && !dataSetExceptions.isEmpty()) {
			for(Long accountNumber:dataSetExceptions.keySet()) {
				DataSetException exception = new DataSetException(accountNumber, dataSetExceptions.get(accountNumber));
				exceptions.add(exception);
			}
		}
		export = new Button("Export Data");
		export.setIcon(new Icon(VaadinIcon.UPLOAD));
		export.addClickListener(e -> exportData());
		exceptionsGrid = new Grid<>(DataSetException.class);
		exceptionsGrid.setColumns("accountNumber","exceptionRecordValue");
		if("firstName".equals(columnName))
			exceptionsGrid.getColumnByKey("exceptionRecordValue").setHeader("First Name");
		else if("mobileNumber".equals(columnName))
			exceptionsGrid.getColumnByKey("exceptionRecordValue").setHeader("Mobile Number");
		else if("loanAccountNumber".equals(columnName))
			exceptionsGrid.getColumnByKey("exceptionRecordValue").setHeader("Loan A/c #");
		else if("branchCode".equals(columnName))
			exceptionsGrid.getColumnByKey("exceptionRecordValue").setHeader("Branch Code");
		else if("principleDue".equals(columnName))
			exceptionsGrid.getColumnByKey("exceptionRecordValue").setHeader("Principle Due");
		else if("interestDue".equals(columnName))
			exceptionsGrid.getColumnByKey("exceptionRecordValue").setHeader("Interest Due");
		else if("interestRate".equals(columnName))
			exceptionsGrid.getColumnByKey("exceptionRecordValue").setHeader("Interest Rate");
		else if("lastInterestPaidDate".equals(columnName))
			exceptionsGrid.getColumnByKey("exceptionRecordValue").setHeader("Last Interest Paid Date");
		else if("npaDate".equals(columnName))
			exceptionsGrid.getColumnByKey("exceptionRecordValue").setHeader("NPA Date");
		exceptionsGrid.setItems(exceptions);
		add(export,exceptionsGrid);
	}
	
	private void exportData() {
		GridExporter
		.newWithDefaults(this.exceptionsGrid)
		.open();
	}
}

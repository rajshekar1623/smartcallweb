package com.aakhya.smartcall.application.transaction.ui;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.service.BranchService;
import com.aakhya.smartcall.application.dashboard.entity.ActivitySummaryUser;
import com.aakhya.smartcall.application.dashboard.entity.CashCollection;
import com.aakhya.smartcall.application.dashboard.service.DashBoardService;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import software.xdev.vaadin.grid_exporter.GridExporter;

public class CashCollectionReport extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072773594503216043L;
	DashBoardService dashBoardService;
	BranchService branchService;
	PaginatedGrid<CashCollection, ?> activitySummaryGrid = new PaginatedGrid<>(CashCollection.class);
	DatePicker fromDate = new DatePicker("From Date");
	DatePicker toDate = new DatePicker("To Date");
	ComboBox<Branch> cluster = new ComboBox<Branch>("Cluster");
	ComboBox<Branch> branch = new ComboBox<Branch>("Branch");
	
	public CashCollectionReport(DashBoardService dashBoardService,BranchService branchService) {
		this.dashBoardService = dashBoardService;
		this.branchService = branchService;
		buildLayout();
	}
	
	private void buildLayout() {
		setSizeFull();
		cluster.setItems(branchService.findClusters());
		cluster.setItemLabelGenerator(Branch::getBranchName);
		cluster.addValueChangeListener(e -> clusterSelected());
		branch.setItems(branchService.findAllBranches(null,null));
		branch.setItemLabelGenerator(Branch::getBranchName);
		Button search = new Button("Search");
		search.addClickListener(click -> searchByFilters());
		Button reset = new Button("Reset");
		reset.addClickListener(click -> reset());
		HorizontalLayout toolBar = new HorizontalLayout();
		toolBar.setWidth(100, Unit.PERCENTAGE);
		toolBar.add(fromDate,toDate,cluster,branch,search,reset);
		toolBar.setVerticalComponentAlignment(Alignment.END, search,reset);
		
		activitySummaryGrid.getStyle().set("border", "2px solid Grey");
		activitySummaryGrid.getStyle().set("border-radius", "10px");
		activitySummaryGrid.setSizeFull();
		activitySummaryGrid.setColumns("employeeId", "userName", "branchName", "accountNumber",
				"customerName", "collectionDate","amountCollected");
		activitySummaryGrid.setPageSize(8);
		activitySummaryGrid.setPaginatorSize(5);
		Grid.Column<?> c1 = activitySummaryGrid.getColumnByKey("employeeId");
		
		Button exportData = new Button("Export Data");
		exportData.addClickListener(click -> exportData(activitySummaryGrid));
		activitySummaryGrid.appendFooterRow().getCell(c1).setComponent(exportData);
		add(toolBar,activitySummaryGrid);
	}

	private void clusterSelected() {
		Branch selectedCluster = cluster.getValue();
		if(null != selectedCluster) {
			branch.setItems(branchService.getChildBranches(selectedCluster));
		}
	}

	private void reset() {
		fromDate.setValue(null);
		toDate.setValue(null);
		cluster.setValue(null);
		branch.setValue(null);
		List<CashCollection> resetItems = new ArrayList<CashCollection>();
		activitySummaryGrid.setItems(resetItems);
	}

	private void searchByFilters() {
		Date fDate = null;
		Date tDate = null;
		LocalDate selectedFromDate = fromDate.getValue();
		if(null != selectedFromDate)
			fDate = Date.from(selectedFromDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		LocalDate selectedToDate = toDate.getValue();
		Branch selectedCluster = cluster.getValue();
		Branch selectedBranch = branch.getValue();
		if(null != selectedToDate)
			tDate = Date.from(selectedToDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		List<CashCollection> cashCollections = dashBoardService.findCashCollections(fDate, tDate, selectedCluster, selectedBranch);
		if(null != cashCollections && !cashCollections.isEmpty())
		activitySummaryGrid.setItems(cashCollections);
		else
			MessageUtils.showPlainNotification("No records found for the search criteria");
	}
	
	private void exportData(Grid<?> grid) {
		GridExporter.newWithDefaults(grid).open();
	}
}

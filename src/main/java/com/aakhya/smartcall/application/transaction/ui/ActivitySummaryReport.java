package com.aakhya.smartcall.application.transaction.ui;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.service.BranchService;
import com.aakhya.smartcall.application.dashboard.entity.ActivitySummary;
import com.aakhya.smartcall.application.dashboard.service.DashBoardService;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import software.xdev.vaadin.grid_exporter.GridExporter;

public class ActivitySummaryReport extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072773594503216043L;
	DashBoardService dashBoardService;
	BranchService branchService;
	PaginatedGrid<ActivitySummary, ?> activitySummaryGrid = new PaginatedGrid<>(ActivitySummary.class);
	DatePicker fromDate = new DatePicker("From Date");
	DatePicker toDate = new DatePicker("To Date");
	ComboBox<Branch> cluster = new ComboBox<Branch>("Cluster");
	ComboBox<Branch> branch = new ComboBox<Branch>("Branch");
	
	public ActivitySummaryReport(DashBoardService dashBoardService,BranchService branchService) {
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
		activitySummaryGrid.setColumns("branchName", "noOfAccountsAssigned", "noOfAcsCalledOnes", "noOfAcsCalledTwice",
				"noOfAcsCalledThrice", "noOfAcsCalledMoreThanThrice");
		activitySummaryGrid.getColumnByKey("noOfAccountsAssigned").setHeader("Assigned");
		activitySummaryGrid.getColumnByKey("noOfAcsCalledOnes").setHeader("Called Once");
		activitySummaryGrid.getColumnByKey("noOfAcsCalledTwice").setHeader("Called Twice");
		activitySummaryGrid.getColumnByKey("noOfAcsCalledThrice").setHeader("Called Thrice");
		activitySummaryGrid.getColumnByKey("noOfAcsCalledMoreThanThrice").setHeader("Called More than 3 times");
		activitySummaryGrid.setPageSize(8);
		activitySummaryGrid.setPaginatorSize(5);
		Grid.Column<?> c1 = activitySummaryGrid.getColumnByKey("noOfAccountsAssigned");
		Grid.Column<?> c2 = activitySummaryGrid.getColumnByKey("noOfAcsCalledOnes");
		Grid.Column<?> c3 = activitySummaryGrid.getColumnByKey("noOfAcsCalledTwice");
		Grid.Column<?> c4 = activitySummaryGrid.getColumnByKey("noOfAcsCalledThrice");
		Grid.Column<?> c5 = activitySummaryGrid.getColumnByKey("noOfAcsCalledMoreThanThrice");
		activitySummaryGrid.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);
		
		HorizontalLayout headerText = new HorizontalLayout();
		headerText.setWidth(100, Unit.PERCENTAGE);
		headerText.add(new Label("     "),new Label("     "),new Label("     "),
				new Label("     "),new Label("     "),new Label("     "),
				new Label("     "),new Label("     "),new Label("     "),
				new Label("     "),new Label("     "),new Label("     "),
				new Label("     "),new Label("     "),new Label("     "),
				new Label("     "),new Label("     "),new Label("     "),
				new Label("     "),new Label("     "),new Label("     "),
				new Label("No. Of Accounts"));
		
		headerText.setAlignItems(Alignment.CENTER);
		HeaderRow headerRow = activitySummaryGrid.prependHeaderRow();
		headerRow.join(c1, c2, c3, c4, c5).setComponent(headerText);
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
		activitySummaryGrid.setItems(dashBoardService.getActivitySummaryBranchWise(fDate,tDate,selectedCluster,selectedBranch));
	}
	
	private void exportData(Grid<?> grid) {
		GridExporter.newWithDefaults(grid).open();
	}
}

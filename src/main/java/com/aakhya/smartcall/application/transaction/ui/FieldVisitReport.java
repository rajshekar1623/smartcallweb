package com.aakhya.smartcall.application.transaction.ui;

import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.service.BranchService;
import com.aakhya.smartcall.application.dashboard.entity.FieldVisitByUser;
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

public class FieldVisitReport extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072773594503216043L;
	DashBoardService dashBoardService;
	BranchService branchService;
	PaginatedGrid<FieldVisitByUser, ?> vistisByUserGrid = new PaginatedGrid<>(FieldVisitByUser.class);
	DatePicker fromDate = new DatePicker("From Date");
	DatePicker toDate = new DatePicker("To Date");
	ComboBox<Branch> cluster = new ComboBox<Branch>("Cluster");
	ComboBox<Branch> branch = new ComboBox<Branch>("Branch");
	
	public FieldVisitReport(DashBoardService dashBoardService,BranchService branchService) {
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
		
		vistisByUserGrid.getStyle().set("border", "2px solid Grey");
		vistisByUserGrid.getStyle().set("border-radius", "10px");
		vistisByUserGrid.setSizeFull();
		vistisByUserGrid.setColumns("userName", "accountNumber", "customerName", "branchLat",
				"branchLon", "meetingDate","meetingLat","meetingLon","distanceFromBranch","custLat",
				"custLon","variance");
		vistisByUserGrid.getColumnByKey("branchLat").setHeader("Latitude");
		vistisByUserGrid.getColumnByKey("branchLon").setHeader("Longitude");
		vistisByUserGrid.getColumnByKey("meetingLat").setHeader("Latitude");
		vistisByUserGrid.getColumnByKey("meetingLon").setHeader("meetingLon");
		vistisByUserGrid.getColumnByKey("custLat").setHeader("Latitude");
		vistisByUserGrid.getColumnByKey("custLon").setHeader("meetingLon");
		vistisByUserGrid.setPageSize(8);
		vistisByUserGrid.setPaginatorSize(5);
		Grid.Column<?> a1 = vistisByUserGrid.getColumnByKey("branchLat");
		Grid.Column<?> a2 = vistisByUserGrid.getColumnByKey("branchLon");
		Grid.Column<?> b1 = vistisByUserGrid.getColumnByKey("meetingLat");
		Grid.Column<?> b2 = vistisByUserGrid.getColumnByKey("meetingLon");
		Grid.Column<?> c1 = vistisByUserGrid.getColumnByKey("custLat");
		Grid.Column<?> c2 = vistisByUserGrid.getColumnByKey("custLon");
		vistisByUserGrid.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);
		
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
		HeaderRow headerRow = vistisByUserGrid.prependHeaderRow();
		headerRow.join(a1, a2).setComponent(new Label("Branch Location"));
		headerRow.join(b1, b2).setComponent(new Label("Customer Location"));
		headerRow.join(c1, c2).setComponent(new Label("Meeting Location"));
		Button exportData = new Button("Export Data");
		exportData.addClickListener(click -> exportData(vistisByUserGrid));
		vistisByUserGrid.appendFooterRow().getCell(c1).setComponent(exportData);
		add(toolBar,vistisByUserGrid);
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
//		Date fDate = null;
//		Date tDate = null;
//		LocalDate selectedFromDate = fromDate.getValue();
//		if(null != selectedFromDate)
//			fDate = Date.from(selectedFromDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//		
//		LocalDate selectedToDate = toDate.getValue();
//		Branch selectedCluster = cluster.getValue();
//		Branch selectedBranch = branch.getValue();
//		if(null != selectedToDate)
//			tDate = Date.from(selectedToDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		vistisByUserGrid.setItems(dashBoardService.findFieldVisitsByUser());
	}
	
	private void exportData(Grid<?> grid) {
		GridExporter.newWithDefaults(grid).open();
	}
}

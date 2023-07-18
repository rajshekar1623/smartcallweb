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
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import software.xdev.vaadin.grid_exporter.GridExporter;

public class ActivitySummaryByUserReport extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8859187806806394025L;
	DashBoardService dashBoardService;
	BranchService branchService;
	DatePicker fromDate = new DatePicker("From Date");
	DatePicker toDate = new DatePicker("To Date");
	ComboBox<Branch> cluster = new ComboBox<Branch>("Cluster");
	ComboBox<Branch> branch = new ComboBox<Branch>("Branch");
	PaginatedGrid<ActivitySummaryUser, ?> activitySummaryUserGrid = new PaginatedGrid<>(ActivitySummaryUser.class);

	public ActivitySummaryByUserReport(DashBoardService dashBoardService, BranchService branchService) {
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
		toolBar.add(fromDate, toDate, cluster, branch, search, reset);
		toolBar.setVerticalComponentAlignment(Alignment.END, search, reset);

		activitySummaryUserGrid.getStyle().set("border", "2px solid Grey");
		activitySummaryUserGrid.getStyle().set("border-radius", "10px");
		activitySummaryUserGrid.setSizeFull();
		activitySummaryUserGrid.setColumns("userName", "branchName", "noOfAccountsAssigned", "notCalled",
				"noOfAcsCalledOnes", "noOfAcsCalledTwice", "noOfAcsCalledThrice", "noOfAcsCalledMoreThanThrice");
		activitySummaryUserGrid.getColumnByKey("noOfAccountsAssigned").setHeader("Assigned");
		activitySummaryUserGrid.getColumnByKey("notCalled").setHeader("Not Called");
		activitySummaryUserGrid.getColumnByKey("noOfAcsCalledOnes").setHeader("Called Once");
		activitySummaryUserGrid.getColumnByKey("noOfAcsCalledTwice").setHeader("Called Twice");
		activitySummaryUserGrid.getColumnByKey("noOfAcsCalledThrice").setHeader("Called Thrice");
		activitySummaryUserGrid.getColumnByKey("noOfAcsCalledMoreThanThrice").setHeader("Called More than 3 times");
		activitySummaryUserGrid.setPageSize(8);
		activitySummaryUserGrid.setPaginatorSize(5);
		Grid.Column<?> c1 = activitySummaryUserGrid.getColumnByKey("noOfAccountsAssigned");
		Grid.Column<?> c11 = activitySummaryUserGrid.getColumnByKey("notCalled");
		Grid.Column<?> c2 = activitySummaryUserGrid.getColumnByKey("noOfAcsCalledOnes");
		Grid.Column<?> c3 = activitySummaryUserGrid.getColumnByKey("noOfAcsCalledTwice");
		Grid.Column<?> c4 = activitySummaryUserGrid.getColumnByKey("noOfAcsCalledThrice");
		Grid.Column<?> c5 = activitySummaryUserGrid.getColumnByKey("noOfAcsCalledMoreThanThrice");
		activitySummaryUserGrid.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);
		HorizontalLayout headerText = new HorizontalLayout();
		headerText.setWidth(100, Unit.PERCENTAGE);
		headerText.add(new Label("     "), new Label("     "), new Label("     "), new Label("     "),
				new Label("     "), new Label("     "), new Label("     "), new Label("     "), new Label("     "),
				new Label("     "), new Label("     "), new Label("     "), new Label("     "), new Label("     "),
				new Label("     "), new Label("     "), new Label("     "), new Label("     "), new Label("     "),
				new Label("     "), new Label("     "), new Label("No. Of Accounts"));

		headerText.setAlignItems(Alignment.CENTER);
		HeaderRow headerRow = activitySummaryUserGrid.prependHeaderRow();
		headerRow.join(c1, c11, c2, c3, c4, c5).setComponent(headerText);
		Button exportData = new Button("Export Data");
		exportData.addClickListener(click -> exportData(activitySummaryUserGrid));
		activitySummaryUserGrid.appendFooterRow().getCell(c1).setComponent(exportData);

		add(toolBar, activitySummaryUserGrid);
	}

	private void clusterSelected() {
		Branch selectedCluster = cluster.getValue();
		if (null != selectedCluster) {
			branch.setItems(branchService.getChildBranches(selectedCluster));
		}
	}

	private void reset() {
		fromDate.setValue(null);
		toDate.setValue(null);
		cluster.setValue(null);
		branch.setValue(null);
		List<ActivitySummaryUser> resetItems = new ArrayList<ActivitySummaryUser>();
		activitySummaryUserGrid.setItems(resetItems);
	}

	private void searchByFilters() {
		Date fDate = null;
		Date tDate = null;
		LocalDate selectedFromDate = fromDate.getValue();
		if (null != selectedFromDate)
			fDate = Date.from(selectedFromDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		LocalDate selectedToDate = toDate.getValue();
		Branch selectedCluster = cluster.getValue();
		Branch selectedBranch = branch.getValue();
		if (null != selectedToDate)
			tDate = Date.from(selectedToDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		List<ActivitySummaryUser> activitySummaries = dashBoardService.getActivitySummaryUserWise(fDate, tDate,
				selectedCluster, selectedBranch);
		if (null != activitySummaries && !activitySummaries.isEmpty())
			activitySummaryUserGrid.setItems(activitySummaries);
		else
			MessageUtils.showPlainNotification("No records found for the search criteria");
	}

	private void exportData(Grid<?> grid) {
		GridExporter.newWithDefaults(grid).open();
	}
}

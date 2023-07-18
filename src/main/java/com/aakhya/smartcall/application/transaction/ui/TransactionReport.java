package com.aakhya.smartcall.application.transaction.ui;

import javax.annotation.security.PermitAll;

import com.aakhya.smartcall.application.admin.service.BranchService;
import com.aakhya.smartcall.application.dashboard.service.DashBoardService;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Route;

@Route(value = "report", layout = TransactionMainLayout.class)
@PermitAll
public class TransactionReport extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4182478651388777644L;
	DashBoardService dashBoardService;
	BranchService branchService;

	public TransactionReport(DashBoardService dashBoardService,BranchService branchService) {
		this.dashBoardService = dashBoardService;
		this.branchService = branchService;
		setSizeFull();
		HorizontalLayout tabLayout = new HorizontalLayout();
		tabLayout.setWidth(100, Unit.PERCENTAGE);
		TabSheet tabSheet = new TabSheet();
		tabSheet.setWidth(100, Unit.PERCENTAGE);

//		tabSheet.add("Activity Summary", new ActivitySummaryReport(dashBoardService,branchService));
		tabSheet.add("Activity Summary By User", new ActivitySummaryByUserReport(dashBoardService,branchService));
		tabSheet.add("Cash Collection Report", new CashCollectionReport(dashBoardService, branchService));
		add(tabSheet);
	}
}

package com.aakhya.smartcall.application.home.views;

import java.util.List;

import javax.annotation.security.PermitAll;

import com.aakhya.smartcall.application.dashboard.entity.ActivitySummary;
import com.aakhya.smartcall.application.dashboard.entity.ActivitySummaryUser;
import com.aakhya.smartcall.application.dashboard.service.DashBoardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PermitAll
@Route(value = "", layout = MainLayout.class)
@PageTitle("Dashboard | Aakhya Smartcall")
public class DashboardView extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7629072409316274961L;
	private final DashBoardService service;

	public DashboardView(DashBoardService service) {
		this.service = service;
		addClassName("dashboard-view");
//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		setSizeFull();
		HorizontalLayout line1 = new HorizontalLayout();
		line1.add(getActivitySummaryBar(), getActivitySummaryUserGauge());
		HorizontalLayout line2 = new HorizontalLayout();
		line2.add(getBarchart1(), getBarchart2());
		add(line1, line2);
	}

	private Component getActivitySummaryBar() {
		List<ActivitySummary> summaries = service.getActivitySummaryByCluster();
		System.out.println("No of ActivitySummary in DashboardView is :: " + summaries.size());
		ActivitySummaryBarChart barchartTest = new ActivitySummaryBarChart(summaries);
		return barchartTest;
	}

	private Component getActivitySummaryUserGauge() {
		List<ActivitySummaryUser> activitySummaryUsers = service.getActivitySummaryUserWise(null, null, null, null);
		ActivityByUserDial activityByUserDial = new ActivityByUserDial(activitySummaryUsers);
		return activityByUserDial;
	}

	private Component getBarchart1() {
		BarChart1 chart1 = new BarChart1();
		return chart1;
	}

	private Component getBarchart2() {
		BarChart2 chart1 = new BarChart2();
		return chart1;
	}
}
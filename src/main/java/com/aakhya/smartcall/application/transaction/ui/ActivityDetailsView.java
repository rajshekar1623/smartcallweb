package com.aakhya.smartcall.application.transaction.ui;

import java.util.List;

import com.aakhya.smartcall.application.transaction.activity.entity.ActivityDetail;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ActivityDetailsView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5175964190914093267L;

	public ActivityDetailsView(List<ActivityDetail> activityDetails) {
		setSizeFull();
		Grid<ActivityDetail> detailGrid = new Grid<>(ActivityDetail.class);
		detailGrid.setSizeFull();
		detailGrid.getStyle().set("border", "2px solid Grey");
		detailGrid.getStyle().set("border-radius", "10px");
		detailGrid.setColumns("attemptDateTime", "attemptDuration", "attemptFlow", "attemptNotes", "scheduleType",
				"scheduleDateTime");
		detailGrid.getColumns().forEach(col -> col.setAutoWidth(true));
		detailGrid.setItems(activityDetails);
		add(detailGrid);
	}
}

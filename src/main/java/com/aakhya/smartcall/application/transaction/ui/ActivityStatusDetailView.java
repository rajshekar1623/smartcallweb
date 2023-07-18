package com.aakhya.smartcall.application.transaction.ui;

import java.util.List;

import com.aakhya.smartcall.application.transaction.activity.entity.ActivityWithDetail;
import com.aakhya.smartcall.application.transaction.activity.service.ActivityService;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class ActivityStatusDetailView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -271461990993936076L;
	ActivityService activityService;
	TransactionDataSet transactionDataSet;
	
	public ActivityStatusDetailView(ActivityService activityService,TransactionDataSet transactionDataSet) {
		this.activityService = activityService;
		this.transactionDataSet = transactionDataSet;
		buildLayout();
	}

	public void buildLayout() {
		setSizeFull();
		Grid<ActivityWithDetail> activityGrid = new Grid<>(ActivityWithDetail.class);
		activityGrid.setSizeFull();
		activityGrid.getStyle().set("border", "2px solid Grey");
		activityGrid.getStyle().set("border-radius", "10px");
		activityGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
		List<ActivityWithDetail> activities = activityService.findActivitiesWithDetail(transactionDataSet.getDataSetId());
		System.out.println("The number of activities for dataSetId :: "+transactionDataSet.getDataSetId()+" is :: "+activities.size());
		activityGrid.setColumns("activityDate","activityTime","userName","activityType");
//		activityGrid.addColumn(new ComponentRenderer<>(activity -> {
//			Button viewDetails = new Button("View Activity Detail");
//			viewDetails.addClickListener(e -> activityDetailView(activity));
//			return viewDetails;
//		}));
		activityGrid.addColumn(new ComponentRenderer<>(activity ->{
			VerticalLayout flow = new VerticalLayout();
			if(null != activity.getSteps() && !activity.getSteps().isEmpty()) {
				for(String step:activity.getSteps()) {
					System.out.println(step);
					flow.add(new Label(step));
				}
				if(null != activity.getAmountCollected() 
						&& !activity.getAmountCollected().isEmpty()
						&& !activity.getAmountCollected().isBlank()) {
					flow.add(new Label(activity.getAmountCollected()));
				}
			}
			return flow;
		})).setHeader("Activity Status");
		activityGrid.setItems(activities);
		activityGrid.getColumns().forEach(col -> col.setAutoWidth(true));
		add(activityGrid);
	}

//	private void activityDetailView(Activity activity) {
//		if(null != activity.getActivityDetails()) {
//			System.out.println("The number of activity details :: "+activity.getActivityDetails().size());
//			
//			Dialog dialog = new Dialog();
//			dialog.setHeaderTitle("Transaction Data Detail");
//			dialog.setHeight("600px");
//			dialog.setWidth("800px");
//			ActivityDetailsView activityDetailsView = new ActivityDetailsView(activity.getActivityDetails());
//			Button cancelButton = new Button("Cancel", e -> dialog.close());
//			dialog.add(activityDetailsView);
//			dialog.getFooter().add(cancelButton);
//			dialog.open();
//		}
//	}
}

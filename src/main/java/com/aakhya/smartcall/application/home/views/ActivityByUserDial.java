package com.aakhya.smartcall.application.home.views;

import java.util.List;

import com.aakhya.smartcall.application.dashboard.entity.ActivitySummaryUser;
import com.storedobject.chart.Chart.Label;
import com.storedobject.chart.Color;
import com.storedobject.chart.Font;
import com.storedobject.chart.GaugeChart;
import com.storedobject.chart.SOChart;
import com.storedobject.chart.Title;
import com.storedobject.chart.Toolbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ActivityByUserDial extends HorizontalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 671293522038833198L;

	public ActivityByUserDial(List<ActivitySummaryUser> activitySummaries) {
		setSizeFull();
		getStyle().set("border", "2px solid Grey");
		getStyle().set("border-radius", "10px");
		SOChart soChart = new SOChart();
		soChart.setSize("600px", "320px");

	    // Gauge chart
	    GaugeChart gc = new GaugeChart();
	    gc.setMin(0);
	    gc.setMax(240);
	    gc.setValue(96);
	    Label label = gc.getAxisLabel(true);
	    label.setFontStyle(new Font(null, Font.Size.number(10)));
	    label.setFormatter("{value}km/h");
	    gc.addDialZone(50, new Color("blue"));
	    gc.addDialZone(80, new Color(255, 246, 0));
	    gc.addDialZone(100, new Color("red"));
	    Toolbox toolbox = new Toolbox();
        toolbox.addButton(new Toolbox.Download(), new Toolbox.Zoom());
        
        // Switching off the default legend
        soChart.disableDefaultLegend();

        // Let's add some titles
        Title title = new Title("Cash Collection");
        title.setSubtext("Collection amounts by Clusters");

	    // Add it to the chart display
	    soChart.add(gc,toolbox,title);

	    // Set the component for the view
	    add(soChart);
	}
	
}

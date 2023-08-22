package com.aakhya.smartcall.application.home.views;

import com.storedobject.chart.SOChart;
import com.storedobject.chart.Size;
import com.storedobject.chart.SunburstChart;
import com.storedobject.chart.TreeData;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SunburstChartDemo extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1892247143464946406L;

	public SunburstChartDemo() {
//	    super("Family Relationships");

	    // Creating a chart display area
		setSizeFull();
		getStyle().set("border", "2px solid Grey");
		getStyle().set("border-radius", "10px");
	    SOChart soChart = new SOChart();
	    soChart.setSize("570px", "300px");

	    SunburstChart chart =
	        new SunburstChart(
	            new TreeData("Grandpa")
	                .add(new TreeData("Father", 10).add("Me", 5).add("Brother Peter", 1))
	                .add(
	                    new TreeData("Uncle Leo", 15)
	                        .add(new TreeData("Cousin Mary", 5).add("Jackson", 2))
	                        .add("Cousin Ben", 4)
	                        .add("Cousin Jack", 2)),
	            new TreeData("Nancy")
	                .add(new TreeData("Uncle Nike").add("Cousin Jetty", 1).add("Cousin Jenny", 2)));
	    chart.setRadius(Size.percentage(90));

	    // Add to the chart display area
	    soChart.add(chart);
	    add(soChart);
	}

}

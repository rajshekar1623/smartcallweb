package com.aakhya.smartcall.application.home.views;

import java.util.ArrayList;
import java.util.List;

import com.aakhya.smartcall.application.dashboard.entity.ActivitySummary;
import com.storedobject.chart.AngleAxis;
import com.storedobject.chart.BarChart;
import com.storedobject.chart.Chart;
import com.storedobject.chart.DataMatrix;
import com.storedobject.chart.DataType;
import com.storedobject.chart.PolarCoordinate;
import com.storedobject.chart.RadiusAxis;
import com.storedobject.chart.SOChart;
import com.storedobject.chart.Title;
import com.storedobject.chart.Toolbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ActivitySummaryBarChart extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8322804033984470719L;
	private final List<Chart> charts = new ArrayList<Chart>();

	public ActivitySummaryBarChart(List<ActivitySummary> summaries) {
		setSizeFull();
		getStyle().set("border", "2px solid Grey");
		getStyle().set("border-radius", "10px");
		SOChart soChart = new SOChart();
		soChart.setSize("600px", "320px");
		DataMatrix dataMatrix = new DataMatrix("Activity Summary By Branches");
		// Preparing data
		String[] branches = new String[summaries.size()];
		Number[] assigned = new Number[summaries.size()];
		Number[] calledOnce = new Number[summaries.size()];
		Number[] calledTwice = new Number[summaries.size()];
		Number[] calledThrice = new Number[summaries.size()];
		int k=0;
		for(ActivitySummary activitySummary:summaries) {
			branches[k] = activitySummary.getBranchName();
			assigned[k] = activitySummary.getNoOfAccountsAssigned();
			calledOnce[k] = activitySummary.getNoOfAcsCalledOnes();
			calledTwice[k] = activitySummary.getNoOfAcsCalledTwice();
			calledThrice[k] = activitySummary.getNoOfAcsCalledThrice();
			k++;
		}
 		// Columns contain products
		dataMatrix.setColumnNames(branches);
		dataMatrix.setColumnDataName("Branches");
		// Rows contain years of production
		dataMatrix.setRowNames("Assigned", "Called Once", "Called Twice", "Called Thrice");
		dataMatrix.setRowDataName("Activity");
		// Add row values
		dataMatrix.addRow(assigned);
		dataMatrix.addRow(calledOnce);
		dataMatrix.addRow(calledTwice);
		dataMatrix.addRow(calledThrice);

		// Define a polar coordinate system with radius and angle.
		RadiusAxis radiusAxis = new RadiusAxis(DataType.CATEGORY);
		AngleAxis angleAxis = new AngleAxis(DataType.NUMBER);
		PolarCoordinate pc = new PolarCoordinate(radiusAxis, angleAxis);

		// Bar chart variable
		BarChart bc;
		// Create a bar chart for each row
		for (int i = 0; i < dataMatrix.getRowCount(); i++) {
			// Bar chart for the row
			bc = new BarChart(dataMatrix.getColumnNames(), dataMatrix.getRow(i));
			bc.setName(dataMatrix.getRowName(i));
			// Plot that to the coordinate system defined
			bc.plotOn(pc);
			// Add that to the chart list
			charts.add(bc);
		}
		charts.forEach(soChart::add);
		
		 Toolbox toolbox = new Toolbox();
	        toolbox.addButton(new Toolbox.Download(), new Toolbox.Zoom());
	        soChart.add(toolbox);
	        // Switching off the default legend
	        soChart.disableDefaultLegend();

	        // Let's add some titles
	        Title title = new Title("Activity Summary");
	        title.setSubtext("Truth is always simple but mostly hidden - Syam");
	        soChart.add(title);
	    add(soChart);
	}
}
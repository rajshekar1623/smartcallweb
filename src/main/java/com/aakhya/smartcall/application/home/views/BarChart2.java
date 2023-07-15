package com.aakhya.smartcall.application.home.views;

import com.storedobject.chart.BarChart;
import com.storedobject.chart.CategoryData;
import com.storedobject.chart.Data;
import com.storedobject.chart.RectangularCoordinate;
import com.storedobject.chart.SOChart;
import com.storedobject.chart.Size;
import com.storedobject.chart.Title;
import com.storedobject.chart.Toolbox;
import com.storedobject.chart.XAxis;
import com.storedobject.chart.YAxis;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BarChart2 extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3900509902817176689L;

	public BarChart2() {
		setSizeFull();
		getStyle().set("border", "2px solid Grey");
		getStyle().set("border-radius", "10px");
		// Creating a chart display area
        SOChart soChart = new SOChart();
        soChart.setSize("570px", "300px");

        // Let us define some inline data
        CategoryData labels =
                new CategoryData("April Fool's Day", "Marriage Day", "Election Day", "Any Other Day");
        Data data = new Data(5, 20, 100, 2);

        // Axes
        XAxis xAxis;
        YAxis yAxis;

        // Bar chart
//        BarChart bc1 = new BarChart(labels, data); // First bar chart
//        xAxis = new XAxis(labels);
//        xAxis.getLabel(true).setRotation(45);
//        yAxis = new YAxis(data);
//        RectangularCoordinate coordinate = new RectangularCoordinate(xAxis, yAxis);
//        bc1.plotOn(coordinate); // Bar chart needs to be plotted on a coordinate system
//        coordinate.getPosition(true).setRight(Size.percentage(60)); // Leave space on the right side

        BarChart bc2 = new BarChart(data, labels); // Second bar chart
        xAxis = new XAxis(data);
        yAxis = new YAxis(labels);
        RectangularCoordinate coordinate = new RectangularCoordinate(xAxis, yAxis);
        bc2.plotOn(coordinate); // Bar chart needs to be plotted on a coordinate system
        coordinate.getPosition(true).setLeft(Size.percentage(60)); // Leave space on the left side

        // Just to demonstrate it, we are creating a "Download" and a "Zoom" toolbox button
        Toolbox toolbox = new Toolbox();
        toolbox.addButton(new Toolbox.Download(), new Toolbox.Zoom());

        // Switching off the default legend
        soChart.disableDefaultLegend();

        // Let's add some titles
        Title title = new Title("Probability of Getting Fooled");
        title.setSubtext("Truth is always simple but mostly hidden - Syam");

        // Add the chart components to the chart display area
        soChart.add(bc2, toolbox, title);

        // Add to the view
        add(soChart);
	}
}

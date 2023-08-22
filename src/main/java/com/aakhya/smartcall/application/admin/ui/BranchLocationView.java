package com.aakhya.smartcall.application.admin.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.service.BranchService;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMap;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMap.GoogleMapClickEvent;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMap.MapType;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMapMarker;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMapMarker.DragEndEvent;
import com.flowingcode.vaadin.addons.googlemaps.LatLon;
import com.flowingcode.vaadin.addons.googlemaps.Markers;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;

public class BranchLocationView extends AbstractGoogleMapsDemo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7043846393572333922L;
//	private static final String apiKey = "AIzaSyDsqxiDX4Pqfn7NUYzKFS2Nn2H4W5ywtaQ";
	private Branch branch;
	private BranchService service;

	public BranchLocationView(Branch branch, BranchService service) {
		super(branch);
		this.branch = branch;
		this.service = service;
	}

	public void saveBranch(Dialog dialog) {
		StringBuffer checkBranch = new StringBuffer();
		checkBranch.append("Branch values are {");
		checkBranch.append("[Branch Name:" + branch.getBranchName() + "]");
		checkBranch.append("[Branch Code:" + branch.getBranchCode() + "]");
		checkBranch.append("[Branch Type:" + branch.getBranchType() + "]");
		checkBranch.append("[Branch EmailId:" + branch.getBranchEmailId() + "]");
		checkBranch.append("[Branch Address:" + branch.getBranchAddress() + "]");
		checkBranch.append("[Branch Branch Pincode:" + branch.getBranchPincode() + "]}");
//		checkBranch.append("[Branch Name:"+branch.getBranchName()+"]");
//		checkBranch.append("[Branch Name:"+branch.getBranchName()+"]");
//		checkBranch.append("[Branch Name:"+branch.getBranchName()+"]");
//		checkBranch.append("[Branch Name:"+branch.getBranchName()+"]");
//		checkBranch.append("[Branch Name:"+branch.getBranchName()+"]");
//		checkBranch.append("[Branch Name:"+branch.getBranchName()+"]");
//		checkBranch.append("[Branch Name:"+branch.getBranchName()+"]");
//		checkBranch.append("[Branch Name:"+branch.getBranchName()+"]");
//		checkBranch.append("[Branch Name:"+branch.getBranchName()+"]");
		System.out.println(checkBranch);
		service.saveBranch(branch);
		MessageUtils.successMessage("Successfully captured location for Branch :: " + branch.getBranchName());
		dialog.close();
		service.getDistance(16.317549, 80.444093, 16.292465, 80.443643);
	}

	@Override
	protected void createGoogleMapsDemo(String apiKey, Branch branch) {
		GoogleMap gmaps = new GoogleMap(apiKey, null, null);
		gmaps.setMapType(MapType.ROADMAP);
		gmaps.setSizeFull();

		if (null != branch && null != branch.getGenericDecimal1() && null != branch.getGenericDecimal2()) {
			System.out.println(
					"Lattitude ::" + branch.getGenericDecimal1() + ", Longitute ::" + branch.getGenericDecimal2());
			gmaps.setCenter(
					new LatLon(branch.getGenericDecimal1().doubleValue(), branch.getGenericDecimal2().doubleValue()));
			gmaps.setZoom(15);
		} else
			gmaps.setCenter(new LatLon(16.5105912, 80.6313494));

		// add click listener to get latitude and longitude on left click
		gmaps.addClickListener(ev -> setBranchLocation(ev));

		// add click listener to get latitude and longitude on right click
		gmaps.addRightClickListener(ev -> setBranchLocation(ev));

		// create marker on map center and add info window to it
//		GoogleMapMarker flowingmarker = gmaps.addMarker("Center", gmaps.getCenter(), false,
//				"https://www.flowingcode.com/wp-content/uploads/2020/06/FCMarker.png");
//		flowingmarker.addInfoWindow("<h1>Flowing Code</h1>");
		
		gmaps.addMarker("Center", gmaps.getCenter(), true, "https://www.flowingcode.com/wp-content/uploads/2020/06/FCMarker.png")
	    .addDragEndEventListener(
	        e -> setBranchLocation(e));


		// add polygon with icons and click listener
//	    GoogleMapPolygon gmp =
//	        gmaps.addPolygon(
//	            Arrays.asList(
//	                new GoogleMapPoint(gmaps.getCenter().getLat(), gmaps.getCenter().getLon() + 1),
//	                new GoogleMapPoint(gmaps.getCenter().getLat() + 1, gmaps.getCenter().getLon()),
//	                new GoogleMapPoint(gmaps.getCenter().getLat(), gmaps.getCenter().getLon() - 1),
//	                new GoogleMapPoint(gmaps.getCenter().getLat() - 1, gmaps.getCenter().getLon()),
//	                new GoogleMapPoint(gmaps.getCenter().getLat(), gmaps.getCenter().getLon() + 1)));
//	    gmp.setClosed(false);
//	    gmp.setIcons(new Icon("M -2,0 0,-2 2,0 0,2 z", "#F00", "#FF0", 1, 25));
//	    gmp.addClickListener(e -> Notification.show("Polygon clicked"));

		// add button to show center coordinates
		Button center = new Button("Show Coordinates", ev -> {
			Notification.show("Center coordinates: " + gmaps.getCenter());
		});

		// add button to toggle visibility of marker's info window
//		Button toggleInfoWindow = new Button("Toggle Info Window", ev -> {
//			flowingmarker.setInfoWindowVisible(!flowingmarker.isInfoWindowVisible());
//		});

		FlexLayout layout = new FlexLayout();
		layout.setFlexWrap(FlexWrap.WRAP);
		center.addClassName("margin-button");
//		toggleInfoWindow.addClassName("margin-button");
		layout.add(center, new Button("Go to current location", e -> gmaps.goToCurrentLocation()));

		gmaps.addCurrentLocationEventListener(
				e -> gmaps.addMarker(new GoogleMapMarker("You are here!", gmaps.getCenter(), false, Markers.GREEN)));

		gmaps.addGeolocationErrorEventListener(e -> Notification
				.show(e.isBrowserHasGeolocationSupport() ? "The geolocation service failed on retrieving your location."
						: "Your browser doesn't support geolocation."));
		add(gmaps, layout);
	}

	private void setBranchLocation(DragEndEvent e) {
		branch.setGenericDecimal1(new BigDecimal(e.getLatitude()).setScale(5, RoundingMode.HALF_UP));
		branch.setGenericDecimal2(new BigDecimal(e.getLongitude()).setScale(5, RoundingMode.HALF_UP));
	}

	private void setBranchLocation(GoogleMapClickEvent ev) {
		branch.setGenericDecimal1(new BigDecimal(ev.getLatitude()).setScale(5, RoundingMode.HALF_UP));
		branch.setGenericDecimal2(new BigDecimal(ev.getLongitude()).setScale(5, RoundingMode.HALF_UP));
	}
}

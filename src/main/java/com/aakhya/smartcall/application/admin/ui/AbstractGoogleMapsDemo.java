package com.aakhya.smartcall.application.admin.ui;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@SuppressWarnings("serial")
@StyleSheet("context://frontend/styles/google-maps/demo-styles.css")
public abstract class AbstractGoogleMapsDemo extends VerticalLayout {

  public AbstractGoogleMapsDemo(Branch branch) {
    this.setSizeFull();
    String apiKey = "AIzaSyDsqxiDX4Pqfn7NUYzKFS2Nn2H4W5ywtaQ";
//    if (apiKey == null) {
//      add(
//          new H2(
//              "Api key is needded to run the demo, pass it using the following system property: '-Dgoogle.maps.api=<your-api-key>'"));
//    } else {
      createGoogleMapsDemo(apiKey,branch);
//    }
  }

  protected abstract void createGoogleMapsDemo(String apiKey,Branch branch);
}

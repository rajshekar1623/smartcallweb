package com.aakhya.smartcall.application.product.ui;

import com.aakhya.smartcall.application.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class ProductMainLayout extends AppLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7804257260842514862L;
	private final SecurityService securityService;
	private Button home;
	private Button admin;
	private Button security;
	private Button transaction;
	private Button product;

	public ProductMainLayout(SecurityService securityService) {
		this.securityService = securityService;
		createHeader();
		createDrawer();
	}

	private void createHeader() {
		H1 logo = new H1("Aakhya Smartcall");
		logo.addClassNames("text-l", "m-m");

		Button logout = new Button("Log out", e -> securityService.logout());
		logout.setIcon(new Icon(VaadinIcon.EXIT));

		admin = new Button("Admin", e -> createAdmin());
		admin.setIcon(new Icon(VaadinIcon.COMPILE));

		security = new Button("Security", e -> createSecurity());
		security.setIcon(new Icon(VaadinIcon.LOCK));

		home = new Button("Home", e -> createHome());
		security.setIcon(new Icon(VaadinIcon.HOME));

		transaction = new Button("Transaction", e -> createTransaction());
		transaction.setIcon(new Icon(VaadinIcon.BAR_CHART_H));

		product = new Button("Product", e -> createProduct());
		product.setIcon(new Icon(VaadinIcon.PACKAGE));

		HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, home, transaction, admin, product,
				security, logout);

		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.expand(logo);
		header.setWidth("100%");
		header.addClassNames("py-0", "px-m");
		header.getStyle().set("border", "2px solid Grey");
		header.getStyle().set("border-radius", "10px");

		addToNavbar(header);

	}

	private void createDrawer() {
		RouterLink productLink = new RouterLink("Product", ProductView.class);
		productLink.setHighlightCondition(HighlightConditions.sameLocation());
		VerticalLayout drawer = new VerticalLayout(productLink);
		drawer.setSizeFull();
		drawer.getStyle().set("border", "2px solid Grey");
		drawer.getStyle().set("border-radius", "10px");
		drawer.add(productLink);
		addToDrawer(drawer);
	}

	private void createHome() {
		home.getUI().ifPresent(ui -> ui.navigate(""));
	}

	private void createAdmin() {
		admin.getUI().ifPresent(ui -> ui.navigate("admin"));
	}

	private void createSecurity() {
		security.getUI().ifPresent(ui -> ui.navigate("security"));
	}

	private void createTransaction() {
		transaction.getUI().ifPresent(ui -> ui.navigate("transaction"));
	}

	private void createProduct() {
		product.getUI().ifPresent(ui -> ui.navigate("product"));
	}
}

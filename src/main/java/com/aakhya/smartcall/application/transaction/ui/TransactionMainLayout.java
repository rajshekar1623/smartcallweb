package com.aakhya.smartcall.application.transaction.ui;

import org.springframework.security.core.userdetails.UserDetails;

import com.aakhya.smartcall.application.security.SecurityService;
import com.aakhya.smartcall.application.security.entity.User;
import com.aakhya.smartcall.application.security.service.UserService;
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



public class TransactionMainLayout extends AppLayout {
	
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
	private UserService userService;
	
	public TransactionMainLayout(SecurityService securityService,UserService userService) {
		this.securityService = securityService;
		this.userService = userService;
		createHeader();
        createDrawer();
	}
	
	private void createHeader() {
        H1 logo = new H1("Aakhya Smartcall  User: "+getUserName());
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
        transaction.setIcon(new Icon(VaadinIcon.PACKAGE));
        
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(),logo,home,transaction,
        		admin,security, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        header.getStyle().set( "border" , "2px solid Grey" ) ; 
        header.getStyle().set("border-radius", "10px");
        
        addToNavbar(header); 

    }

	private void createDrawer() {
		RouterLink listLink = new RouterLink("Member List", AssignmentView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        
        RouterLink transactionDataUpload = new RouterLink("File Upload", TransactionDataUpload.class);
        
        RouterLink activityStatus = new RouterLink("Activity Status",ActivityStatusView.class);
        
        RouterLink sendSms = new RouterLink("Send SMS",SendSmsView.class);
        
        RouterLink sendWhatsapp = new RouterLink("Send Whatsapp",SendWhatsappView.class);
        RouterLink report = new RouterLink("Report",TransactionReport.class);
        VerticalLayout drawer = new VerticalLayout(listLink,transactionDataUpload,activityStatus,sendSms,sendWhatsapp,report);
        drawer.setSizeFull();
        drawer.getStyle().set( "border" , "2px solid Grey" ) ; 
        drawer.getStyle().set("border-radius", "10px");
        addToDrawer(drawer);
	}
	
	private void createHome() {
    	home.getUI().ifPresent(ui ->
        ui.navigate(""));
    }
	
	private void createAdmin() {
    	admin.getUI().ifPresent(ui ->
        ui.navigate("admin"));
    }
    
    private void createSecurity() {
    	security.getUI().ifPresent(ui ->
        ui.navigate("security"));
    }
    
    private void createTransaction() {
    	security.getUI().ifPresent(ui ->
        ui.navigate("transaction"));
    }
    
    private void createProduct() {
		product.getUI().ifPresent(ui -> ui.navigate("product"));
	}
    
    private String getUserName() {
    	UserDetails user = securityService.getAuthenticatedUser();
    	User authenticatedUser = userService.findUserById(user.getUsername());
    	return authenticatedUser.getUserName();
    }
}

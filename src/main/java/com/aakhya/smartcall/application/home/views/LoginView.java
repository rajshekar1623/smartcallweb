package com.aakhya.smartcall.application.home.views;

import com.aakhya.smartcall.application.security.service.UserService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login") 
@PageTitle("Login | Aakhya Smartcall")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4240833353318078949L;
	private final LoginForm login = new LoginForm(); 

	public LoginView(UserService userService){
		addClassName("login-view");
		setSizeFull(); 
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
		add(new H1("Aakhya Smartcall"), login);
		login.setAction("login"); 
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		if(beforeEnterEvent.getLocation()  
        .getQueryParameters()
        .getParameters()
        .containsKey("error")) {
            login.setError(true);
        }
	}
}
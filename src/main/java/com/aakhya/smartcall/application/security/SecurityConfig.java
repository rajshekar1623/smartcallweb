package com.aakhya.smartcall.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aakhya.smartcall.application.home.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/images/**").permitAll().antMatchers("/").permitAll()
				.antMatchers("/login").permitAll().antMatchers("/admin").hasAuthority("Admin").antMatchers("/security")
				.permitAll().antMatchers("/reports").permitAll().antMatchers("/dashboardapi").permitAll().and().httpBasic();
		http.csrf().disable();
		super.configure(http);
		setLoginView(http, LoginView.class);
		http.cors().and().csrf().ignoringAntMatchers("/dashboardapi/**")
		.ignoringAntMatchers("/security/**");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/dashboardapi/**")
		.antMatchers("/security/**");
		super.configure(web);
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
}
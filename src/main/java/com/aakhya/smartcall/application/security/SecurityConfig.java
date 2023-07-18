package com.aakhya.smartcall.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
    	http.csrf().disable();
        http.authorizeRequests().antMatchers("/images/**").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/admin")
        .hasAuthority("Admin")
        .antMatchers("/security").permitAll()
        .and()
        .httpBasic();
        super.configure(http);
        setLoginView(http, LoginView.class);
    }
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(new BCryptPasswordEncoder());
    	return provider;
    }
}
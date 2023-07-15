package com.aakhya.smartcall.application.security.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aakhya.smartcall.application.security.entity.User;
import com.aakhya.smartcall.application.security.service.UserService;

@RestController
@RequestMapping("/security")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(path="/generateUser", method = RequestMethod.GET, produces =MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@RequestParam("userId") String userId) {
		return userService.findUserById(userId);
	}
	
	@RequestMapping(path="/generateOtp", method = RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE)
	public User generateOtpAndSendSms(@RequestBody User user) {
		try {
			return userService.generateOtpAndSendSms(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new User();
		}
	}
	
	@RequestMapping(path="/validateOtp", method = RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE)
	public User validateOtp(@RequestBody User user) {
		try {
			return userService.validateOtp(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new User();
		}
	}
}

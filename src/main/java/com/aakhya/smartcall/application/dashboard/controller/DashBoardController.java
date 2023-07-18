package com.aakhya.smartcall.application.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aakhya.smartcall.application.dashboard.entity.DashBoard;
import com.aakhya.smartcall.application.dashboard.service.DashBoardService;
import com.aakhya.smartcall.application.security.entity.User;



@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

	@Autowired
	private DashBoardService dashBoardService;
	
	@RequestMapping(path="/getDashBoardForUser", method = RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE)
	public List<DashBoard> getDashBoardForUser(@RequestBody User user){
		return dashBoardService.getDashBoardForUser(user);
	}
}

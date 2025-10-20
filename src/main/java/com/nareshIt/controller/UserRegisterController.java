package com.nareshIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nareshIt.entity.UserRegister;
import com.nareshIt.service.UserRegisterService;

@RestController
@RequestMapping("api")
public class UserRegisterController {
	
	@Autowired UserRegisterService userRegisterService;
	
	@PostMapping("/userregister")
	public String createUserRegister(@RequestBody UserRegister userRegister) {
		
		userRegisterService.insertUserRegister(userRegister);
		
		return "User register saved successfully";
	}

}

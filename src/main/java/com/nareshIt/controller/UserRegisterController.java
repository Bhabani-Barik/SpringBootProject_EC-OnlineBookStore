package com.nareshIt.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nareshIt.entity.UserRegister;
import com.nareshIt.model.ResponseMessage;
import com.nareshIt.service.UserRegisterService;
import com.nareshIt.utility.Constants;

@RestController
@RequestMapping("api")
public class UserRegisterController {

	@Autowired
	UserRegisterService userRegisterService;

	@PostMapping("/userregisters")
	public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody UserRegister userRegister) {

		String insertUserRegister = userRegisterService.insertUserRegister(userRegister);

		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"user registered successfully", insertUserRegister));
	}

}

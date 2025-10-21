package com.nareshIt.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nareshIt.entity.UserRegister;
import com.nareshIt.repository.UserRegisterRepo;
import com.nareshIt.service.UserRegisterService;

@Component
public class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	UserRegisterRepo userRegisterRepo;

	@Override
	public String insertUserRegister(UserRegister userRegister) {
		userRegisterRepo.save(userRegister);
		return "save successfully";
	}

}

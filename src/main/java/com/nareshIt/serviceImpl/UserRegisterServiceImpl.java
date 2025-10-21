package com.nareshIt.serviceImpl;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nareshIt.entity.UserRegister;
import com.nareshIt.model.UserRequestDto;
import com.nareshIt.repository.UserRegisterRepo;
import com.nareshIt.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	UserRegisterRepo userRegisterRepo;

	@Override
	public UserRegister insertUserRegister(UserRequestDto userRequestDto) {
		
		UserRegister user = new UserRegister();
		
		try {
			user.setFirstName(userRequestDto.getFirstName());
			user.setLastName(userRequestDto.getLastName());
			user.setEmail(userRequestDto.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
			user.setContactId(userRequestDto.getContactId());
			userRegisterRepo.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}

}

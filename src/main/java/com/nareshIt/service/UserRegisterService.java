package com.nareshIt.service;

import com.nareshIt.entity.UserRegister;
import com.nareshIt.model.UserRequest;
import com.nareshIt.model.UserRequestDto;

public interface UserRegisterService {

	public UserRegister insertUserRegister(UserRequestDto userRequestDto);
	
	public UserRequest getUserRegisterDetails(Long  id);

	public UserRegister checkUserDetails(UserRequestDto userRequestDto);

}

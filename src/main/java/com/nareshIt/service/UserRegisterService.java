package com.nareshIt.service;

import com.nareshIt.entity.UserRegister;
import com.nareshIt.model.UserRequestDto;

public interface UserRegisterService {

	public UserRegister insertUserRegister(UserRequestDto userRequestDto);

}

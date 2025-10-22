package com.nareshIt.serviceImpl;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nareshIt.entity.UserRegister;
import com.nareshIt.model.UserRequest;
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
	


//	@Override
//	public UserRegister checkUserDetails(UserRequestDto userRequestDto) {
//		
//		UserRegister findbyEmail = userRegisterRepo.findByEmail(userRequestDto.getEmail());
//		
//		//Decoding the Password
//		if(findbyEmail!=null) {
//			
//			String decode = new String(Base64.getDecoder().decode(findbyEmail.getPassword()));
//			
//			if(decode.equals(userRequestDto.getPassword())) {
//				
//				return findbyEmail;
//			}
//			
//		}
//		return findbyEmail;
//	}
	
	@Override
	public UserRegister checkUserDetails(UserRequestDto userRequestDto) {
		return Optional.ofNullable(userRegisterRepo.findByEmail(userRequestDto.getEmail())).filter(
				user -> new String(Base64.getDecoder().decode(user.getPassword())).equals(userRequestDto.getPassword()))
				.orElse(null);
	}

	@Override
	public UserRequest getUserRegisterDetails(Long id) {
		 Optional<UserRegister> byId = userRegisterRepo.findById(id);
		UserRegister userRegister = byId.get();
		return new UserRequest(userRegister.getFirstName(), userRegister.getLastName(), userRegister.getEmail());
	}


}

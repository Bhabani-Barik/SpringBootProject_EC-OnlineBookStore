package com.nareshIt.serviceImpl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nareshIt.entity.FilesEntity;
import com.nareshIt.entity.UserRegister;
import com.nareshIt.model.UserRequest;
import com.nareshIt.model.UserRequestDto;
import com.nareshIt.repository.FileRepo;
import com.nareshIt.repository.UserRegisterRepo;
import com.nareshIt.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	UserRegisterRepo userRegisterRepo;
	
	@Autowired private FileRepo fileRepo;


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



	@Override
	public UserRegister uploadMultiUserRegister(UserRequestDto userRequestDto, MultipartFile[] files) {
		
		UserRegister user = new UserRegister();
		
		try {
			user.setFirstName(userRequestDto.getFirstName());
			user.setLastName(userRequestDto.getLastName());
			user.setEmail(userRequestDto.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
			user.setContactId(userRequestDto.getContactId());
			userRegisterRepo.save(user);
			
			if (files != null && files.length > 0) {
				for (MultipartFile multipartFile : files) {
					FilesEntity fss = new FilesEntity();
					fss.setFileName(multipartFile.getOriginalFilename());
					fss.setFileType(multipartFile.getContentType());
					fss.setData(multipartFile.getBytes());
					fileRepo.save(fss);
				}
			}
		} catch( Exception e) {
			e.printStackTrace();
		}
			
		return user;
	}
	
	@Override
	@Cacheable(value = "getAllUsers")
	public List<UserRegister> getAllUsersRegisterDetails() {
	 List<UserRegister> list = userRegisterRepo.findAll();
	 System.err.println("get the records from Database...............!");
	 return list;
	}
	
	


}

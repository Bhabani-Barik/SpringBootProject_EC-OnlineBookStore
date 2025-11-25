package com.nareshIt.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nareshIt.entity.UserRegister;
import com.nareshIt.model.UserRequestDto;
import com.nareshIt.repository.UserRegisterRepo;
import com.nareshIt.serviceImpl.UserRegisterServiceImpl;

@SpringBootTest
public class UserRegisterServiceTest {
	@MockBean
	private UserRegisterRepo userRegisterRepo; // Fake DB

	@Autowired
	private UserRegisterServiceImpl userRegisterService; // Original service

	@Test
	public void testInsertUserRegister() {

		// Step 1: Create Input Data (like from Postman or frontend)
		UserRequestDto input = new UserRequestDto();
		input.setFirstName("Kamal");
		input.setLastName("Kalyan");
		input.setEmail("kamal@gmail.com");
		input.setPassword("pass@123");

		// Step 2: Create Fake DB Output (as if user saved in DB)
		UserRegister savedUser = new UserRegister();
		savedUser.setId(1L);
		savedUser.setFirstName("Srinu");
		savedUser.setLastName("Lateesha");
		savedUser.setEmail("gopi@gmail.com");
		savedUser.setPassword(Base64.getEncoder().encodeToString("pass@123".getBytes()));

		when(userRegisterRepo.save(any(UserRegister.class))).thenReturn(savedUser);

		// Step 4: Call actual service method
		UserRegister result = userRegisterService.insertUserRegister(input);

		// Step 5: Check (Verify) output
		assertNotNull(result); // result should not be null
		assertEquals("Kamal", result.getFirstName());
		assertEquals("kamal@gmail.com", result.getEmail());
		// save() called only once
		verify(userRegisterRepo, times(1)).save(any(UserRegister.class));
	}
}

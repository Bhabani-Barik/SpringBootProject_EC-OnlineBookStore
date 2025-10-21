package com.nareshIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nareshIt.entity.UserRegister;

@Repository // It's Optional
public interface UserRegisterRepo extends JpaRepository<UserRegister, Long> {
	
	public UserRegister findByEmail(String email);

}

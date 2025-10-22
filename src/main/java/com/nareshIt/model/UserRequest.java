package com.nareshIt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
	
	private String firstName;
	private String lastName;
	private String email;
}

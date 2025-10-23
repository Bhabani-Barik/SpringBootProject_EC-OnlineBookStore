package com.nareshIt.exceptions;

public class CustomerIDNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public CustomerIDNotFoundException(String msg) {
		super(msg);
	}
}

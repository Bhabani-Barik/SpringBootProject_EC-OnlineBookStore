package com.nareshIt.service;

import java.util.List;

import com.nareshIt.entity.Customer;

public interface CustomerService {
	
	public Customer insertCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public Customer createdOrUpdateCustomer(Customer customer);

	public Customer getByCustomerId(Long id);

	public List<Customer> getByAllCustomer();
}

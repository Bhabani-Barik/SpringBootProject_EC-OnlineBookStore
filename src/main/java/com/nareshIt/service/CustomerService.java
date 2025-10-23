package com.nareshIt.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nareshIt.entity.Customer;

public interface CustomerService {
	
	public Customer insertCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public Customer createdOrUpdateCustomer(Customer customer);

	public Customer getByCustomerId(Long id);

	public List<Customer> getByAllCustomer();
	
	public Page<Customer> getByAllCustomersWithPaginations(int page, int size, String sortField, String pageDir);

}

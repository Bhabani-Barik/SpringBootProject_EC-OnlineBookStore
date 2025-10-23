package com.nareshIt.service;

import com.nareshIt.entity.Customer;

public interface CustomerService {
	
	public Customer insertCustmers(Customer customer);

	public Customer UpdatessCustmers(Customer customer);

	public Customer createdOrUpdatessCustmers(Customer customer);

	public Customer getByCustmersId(Long id);

}

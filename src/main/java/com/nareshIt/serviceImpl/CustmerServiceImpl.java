package com.nareshIt.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nareshIt.entity.Customer;
import com.nareshIt.repository.CustmerRepository;
import com.nareshIt.service.CustomerService;

@Service
public class CustmerServiceImpl implements  CustomerService{
	
	@Autowired
	CustmerRepository custmerRepoo;

	@Override
	public Customer insertCustomer(Customer customer) {

		Customer cus = custmerRepoo.save(customer);

		return cus;
	}

	@Override
	public Customer updateCustomer(Customer customer) {

		Customer cus = custmerRepoo.save(customer);

		return cus;
	}

	@Override
	public Customer createdOrUpdateCustomer(Customer customer) {

		if (customer.getId() == null) {

			custmerRepoo.save(customer);

		} else {

			Optional<Customer> byId = custmerRepoo.findById(customer.getId());
			if (byId.isPresent()) {
				Customer existData = byId.get();
				existData.setName(customer.getName());
				existData.setEmail(customer.getEmail());
				custmerRepoo.save(existData);

			} else {

				throw new RuntimeException("Custmer Not Found");
			}
		}
		return customer;
	}

	@Override
	public Customer getByCustomerId(Long id) {
		Optional<Customer> byId = custmerRepoo.findById(id);
		if (!byId.isPresent()) {

			throw new RuntimeException("Custmer Id Not Found");
		}
		return byId.get();
	}
	
	@Override
	public List<Customer> getByAllCustomer() {
		List<Customer> list = custmerRepoo.findAll();

		return list;
	}
}

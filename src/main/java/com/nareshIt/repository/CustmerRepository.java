package com.nareshIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nareshIt.entity.Customer;

@Repository // It's optional
public interface CustmerRepository extends JpaRepository<Customer, Long> {

}

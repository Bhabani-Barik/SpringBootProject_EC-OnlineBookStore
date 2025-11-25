package com.nareshIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nareshIt.entity.BooksEntity;
import com.nareshIt.entity.CartEntity;
import com.nareshIt.entity.Customer;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
	public CartEntity findByCustomerAndBooksEntity(Customer customer, BooksEntity booksEntity);
}

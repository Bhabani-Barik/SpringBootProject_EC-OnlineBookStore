package com.nareshIt.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nareshIt.entity.BooksEntity;
import com.nareshIt.entity.CartEntity;
import com.nareshIt.entity.Customer;
import com.nareshIt.exceptions.BookIdNotFoundException;
import com.nareshIt.exceptions.CustomerIDNotFoundException;
import com.nareshIt.repository.BookRepository;
import com.nareshIt.repository.CartRepository;
import com.nareshIt.repository.CustmerRepository;
import com.nareshIt.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired CartRepository cartRepository;
	@Autowired CustmerRepository customerRepository;
	@Autowired BookRepository bookRepository;
	
	@Override
	public CartEntity addToCart(Long customerId, Long bookId, int quantity) {
		
		// Step: 01 - Check if customer exist in DB using ID
		// If not found, throw custom exception "Customer ID Not Found"
		
		Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerIDNotFoundException("Customer ID Not found"));
		
		 //  Step 2: Check if book exists in DB using ID
	    // If not found, throw custom exception "Book Id not Found"
	    BooksEntity booksEntity = bookRepository.findById(bookId)
	        .orElseThrow(() -> new BookIdNotFoundException("Book Id not Found"));
	    
	    
	    //  Step 3: Check whether this customer already added this book in their cart
	    // It helps to avoid duplicate entries for the same book
	    CartEntity cartItem = cartRepository.findByCustomerAndBooksEntity(customer, booksEntity);
	    
	    //  Step 4: If cartItem already exists, update quantity
	    if (cartItem != null) {
	        // Add the new quantity to existing one
	        cartItem.setQuantity(cartItem.getQuantity() + quantity);
	    } 
	    
	    //  Step 5: If cartItem not found, create new cart record
	    else {
	        // Create new CartModule object with quantity, book, and customer
	        cartItem = new CartEntity(quantity, booksEntity, customer);
	    }
	    

	    //  Step 6: Calculate total price for that book (quantity × book price)
	    cartItem.setTotalPrice(cartItem.getQuantity() * booksEntity.getPrice());
		
	    
	    //  Step 7: Save updated/created cart record to DB
	    // This ensures cart data is persistent in database
	    return cartRepository.save(cartItem);
	}
	
	@Override
	public void deleteToCart(Long id) {
		
		cartRepository.deleteById(id);
		
	}

}

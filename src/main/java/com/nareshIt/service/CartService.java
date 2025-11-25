package com.nareshIt.service;

import com.nareshIt.entity.CartEntity;

public interface CartService {
	
	public CartEntity addToCart(Long customerId, Long bookId, int quantity);
	
	public void deleteToCart(Long id);


}

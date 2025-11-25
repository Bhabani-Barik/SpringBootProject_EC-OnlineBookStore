package com.nareshIt.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nareshIt.entity.CartEntity;
import com.nareshIt.model.ResponseMessage;
import com.nareshIt.service.CartService;
import com.nareshIt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CartController", description = "Add to cart") // swagger annotation
@RestController
public class CartController {

	@Autowired
	CartService cartService;

	@Operation(summary = "Add to cart", description = " e-commerece online books store, add to cart")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Add to cart successfully."),
			@ApiResponse(responseCode = "400", description = "Add to cart operation failed."),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@PostMapping("/addToCart")
	public ResponseEntity<ResponseMessage> addToCarts(@RequestParam Long customerId, @RequestParam Long bookId,
			@RequestParam int quantity) {

		try {

			CartEntity cart = cartService.addToCart(customerId, bookId, quantity);

			if (cart != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
						"Added into cart successfully", cart));
			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Failed to added into cart", cart));
			}

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILURE,
					"Internal Server Error"));
		}

	}

	@Operation(summary = "Remove From Cart", description = "e-commerce online book store, remove from cart")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Removed from the cart successfully"),
			@ApiResponse(responseCode = "200", description = "Removed from the cart operation failed"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@DeleteMapping("/removeFromCart/{id}")
	public ResponseEntity<ResponseMessage> deleteCart(@PathVariable Long id) {
		cartService.deleteToCart(id);
		return ResponseEntity.ok(
				new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Removed from  cart successfully"));

	}
}

package com.nareshIt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nareshIt.entity.Customer;
import com.nareshIt.model.ResponseMessage;
import com.nareshIt.service.CustomerService;
import com.nareshIt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CustomerController", description = "Customer Controller") // swagger annotation
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired CustomerService custmerService;
	

	 @Operation(summary = "Create Custmers",description = "e commerece online books store  create the customer")
	    @ApiResponses({
	     @ApiResponse(responseCode = "201",description = "customer created successfully"),
	     @ApiResponse(responseCode = "400",description = "customer created operation failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	@PostMapping("/custmersave")
		public ResponseEntity<ResponseMessage> createCustmers(@RequestBody Customer customer) {

			try {
				if (customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getName() == null
						|| customer.getName().isEmpty()) {

					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
							HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and name cannot be empty"));
				}

				Customer insertCustmers = custmerService.insertCustomer(customer);

				if (insertCustmers != null) {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
							"custmer save successfully", insertCustmers));
				} else {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
							"custmer Failed", insertCustmers));

				}

			} catch (Exception e) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
						"Internal server error"));
			}
		}
	 
	 
	 @Operation(summary = "Update Custmers",description = "e commerece online books store update the customer")
	    @ApiResponses({
	     @ApiResponse(responseCode = "201",description = "Customer updated successfully"),
	     @ApiResponse(responseCode = "400",description = "Customer update operation failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	    @PutMapping("/updatesCustmer")
		public ResponseEntity<ResponseMessage> custmerUpdates(@RequestBody Customer customer) {

			try {
				if (customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getName() == null
						|| customer.getName().isEmpty()) {

					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
							HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and name cannot be empty"));
				}
				if (customer.getId() == null) {

					Customer insertCustmers = custmerService.updateCustomer(customer);
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
							"custmer update successfully", insertCustmers));

				} else {
					Customer insertCustmers = custmerService.insertCustomer(customer);
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
							"custmer updated successfully", insertCustmers));

				}
			} catch (Exception e) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
						"Internal server error"));
			}
		}
	 
	 
	 
	 @Operation(summary = "Create or Update Custmers",description = "e commerece online books store  create or update customer")
	    @ApiResponses({
	     @ApiResponse(responseCode = "201",description = "customer created or updated successfully"),
	     @ApiResponse(responseCode = "400",description = "customer created or updated operation failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	    @PostMapping("/createdOrUpdatesCustmer")
		public ResponseEntity<ResponseMessage> custmerORUpdates(@RequestBody Customer customer) {

			try {
				if (customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getName() == null
						|| customer.getName().isEmpty()) {

					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
							HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and name cannot be empty"));
				}
				if (customer.getId() == null) {

					Customer createdOrUpdatessCustmers = custmerService.createdOrUpdateCustomer(customer);
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
							"custmer saved successfully", createdOrUpdatessCustmers));

				} else {
					Customer createdOrUpdatessCustmers = custmerService.createdOrUpdateCustomer(customer);
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
							"custmer updated successfully", createdOrUpdatessCustmers));

				}
			} catch (Exception e) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
						"Internal server error"));
			}
		}
	  
	 
	 
	 @Operation(summary = "Get Customer By ID",description = "e commerece online books store  Get Customer By ID")
	    @ApiResponses({
	     @ApiResponse(responseCode = "200",description = "customer by id retrieved successfully"),
	     @ApiResponse(responseCode = "400",description = "customer by id operation failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	        @GetMapping("/getByCustmerId/{id}")
			public ResponseEntity<ResponseMessage> getCustomerByID(@PathVariable Long id) {

				Customer byCustmersId = custmerService.getByCustomerId(id);
				if (byCustmersId != null) {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
							"custmer id getting successfully", byCustmersId));

				} else {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
							"custmer id getting Failed", byCustmersId));

				}
			}
	        
	 
	 @Operation(summary = "Get All Customers",description = "e commerece online books store, get all customers")
	    @ApiResponses({
	     @ApiResponse(responseCode = "200",description = " All customer retrieved successfully"),
	     @ApiResponse(responseCode = "400",description = " getAllOperation operation failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	        @GetMapping("/getAllCustmers")
	  		public ResponseEntity<ResponseMessage> getAllCustmerss() {
	  		   
	  			 List<Customer> byAllCustmers = custmerService.getByAllCustomer();
	  			if(byAllCustmers!=null) {
	  		       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer  getting all  successfully", byAllCustmers));
	  			
	  			 }else {
	  				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer id getting Failed", byAllCustmers));

	  			 }
	  		}  
	 
	 
	 
	 @Operation(summary = "Get All Custmers with Pagination",description = "e commerece online books store, Get All Custmers with Pagination")
	    @ApiResponses({
	     @ApiResponse(responseCode = "200",description = "Customer Data retrived successfully"),
	     @ApiResponse(responseCode = "400",description = "Customer Data retrieved operation failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
			@GetMapping("/getAllCustmerswithpagination")
			public ResponseEntity<ResponseMessage> getByAllCustmerpagination(@RequestParam int page,
					@RequestParam int size, @RequestParam String sortField, @RequestParam String pageDir) {

				Page<Customer> byAllCustmersWithPaginations = custmerService.getByAllCustomersWithPaginations(page, size,
						sortField, pageDir);

				if (byAllCustmersWithPaginations != null) {

					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
							"All Custemers getting with pagination successfully", byAllCustmersWithPaginations));
				} else {

					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
							"All Custemers getting  pagination Failure", byAllCustmersWithPaginations));

				}

			}
}

package com.nareshIt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nareshIt.entity.Customer;
import com.nareshIt.model.ResponseMessage;
import com.nareshIt.service.CustomerService;
import com.nareshIt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired CustomerService custmerService;
	

	 @Operation(summary = "Create User Custmers",description = "e commerece online books store  register the users")
	    @ApiResponses({
	     @ApiResponse(responseCode = "201",description = "user register successfully"),
	     @ApiResponse(responseCode = "400",description = "user register failure"),
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
	  
	 
	        @GetMapping("/getByCustmerId/{id}")
			public ResponseEntity<ResponseMessage> custmerORUpdates(@PathVariable Long id) {

				Customer byCustmersId = custmerService.getByCustomerId(id);
				if (byCustmersId != null) {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
							"custmer id getting successfully", byCustmersId));

				} else {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
							"custmer id getting Failed", byCustmersId));

				}
			}
	        
	        @GetMapping("/getAllCustmers")
	  		public ResponseEntity<ResponseMessage> getAllCustmerss() {
	  		   
	  			 List<Customer> byAllCustmers = custmerService.getByAllCustomer();
	  			if(byAllCustmers!=null) {
	  		       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer  getting all  successfully", byAllCustmers));
	  			
	  			 }else {
	  				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer id getting Failed", byAllCustmers));

	  			 }
	  		}  
}

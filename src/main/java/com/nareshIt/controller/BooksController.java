package com.nareshIt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nareshIt.entity.BooksEntity;
import com.nareshIt.model.ResponseMessage;
import com.nareshIt.service.BooksService;
import com.nareshIt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class BooksController {

	@Autowired
	BooksService booksService;

	@Operation(summary = "Create User Custmers", description = "e commerece online books store, add books")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "user books saved successfully"),
			@ApiResponse(responseCode = "400", description = "user books saved failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/savebooks")
	public ResponseEntity<ResponseMessage> createBooks(@RequestBody BooksEntity booksEntity) {

		try {
			if (booksEntity.getName() == null || booksEntity.getName().isEmpty() || booksEntity.getTitle() == null
					|| booksEntity.getTitle().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "books name and title cannot be empty"));
			}

			BooksEntity custmerSaveBooks = booksService.custmerSaveBooks(booksEntity);

			if (custmerSaveBooks != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
						"custmer  books save successfully", custmerSaveBooks));
			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"custmer  books save Failed", custmerSaveBooks));

			}

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error"));
		}
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<ResponseMessage> getAllBook() {
		try {
			List<BooksEntity> getAllCusBooks = booksService.custmergetAllBooks();
			if (getAllCusBooks != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"custmer All books getting successfully", getAllCusBooks));
			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"custmer All books getting failed", getAllCusBooks));
			}

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error"));
		}
	}

	@GetMapping("/getCustomerBookByID/{id}")
	public ResponseEntity<ResponseMessage> getByBook(@PathVariable Long id) {
		try {
			BooksEntity byCustmerBookid = booksService.getByCustmerBookid(id);
			if (byCustmerBookid != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"custmer Book Id getting successfully", byCustmerBookid));
			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"custmer Book Id getting  failed", byCustmerBookid));
			}

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error"));
		}
	}
}

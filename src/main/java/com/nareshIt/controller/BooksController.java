package com.nareshIt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "BooksController", description = "Add, remove, modify the Books.") // swagger annotation
@RestController
@RequestMapping("/books")
public class BooksController {
	
	
	// Logger
	private static final Logger logger = LoggerFactory.getLogger(UserRegisterController.class);


	@Autowired
	BooksService booksService;

	@Operation(summary = "Add Book", description = "e commerece online books store, add book")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "book saved successfully"),
			@ApiResponse(responseCode = "400", description = "book saved failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/savebooks")
	public ResponseEntity<ResponseMessage> createBooks(@RequestBody BooksEntity booksEntity) {

		logger.info("Add Book controller layer calling or started");

		try {
			if (booksEntity.getName() == null || booksEntity.getName().isEmpty() || booksEntity.getTitle() == null
					|| booksEntity.getTitle().isEmpty()) {
				
				logger.debug("Recived booksEntity: {} ", booksEntity);
				logger.warn("missing book name and book title book ,add book request");
				logger.error("Book Add operation book name or book title missing : Bad book data ");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "books name and title cannot be empty"));
			}

			BooksEntity custmerSaveBooks = booksService.custmerSaveBooks(booksEntity);

			if (custmerSaveBooks != null) {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_BOOK_ADDED_SUCCESS\" .");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
						"custmer  books save successfully", custmerSaveBooks));
			} else {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_BOOK_ADD_OPERATION_FAILED\" .");
				logger.info("Add Book controller layer calling completed");
				logger.warn("Book service return null : book add operation failed");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"custmer  books save Failed", custmerSaveBooks));

			}

		} catch (Exception e) {
			logger.error("Book add operation process failed in Bookstore-DB . Exception:" + e.getMessage());
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error"));
		}
	}

	@Operation(summary = "Get All Books", description = "e commerece online books store, get all book")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "book retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "books retrieve operation failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/getAllBooks")
	public ResponseEntity<ResponseMessage> getAllBook() {
		logger.info("Get All Books controller layer calling or started");
		try {
			List<BooksEntity> getAllCusBooks = booksService.custmergetAllBooks();
			if (getAllCusBooks != null) {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_GET_ALL_BOOK_SUCCESS\" .");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"custmer All books getting successfully", getAllCusBooks));
			} else {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_GET_ALL_BOOK_OPERATION_FAILED\" .");
				logger.info("Get All Books controller layer calling completed");
				logger.warn("Book service return null : get all book operation failed");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"custmer All books getting failed", getAllCusBooks));
			}

		} catch (Exception e) {
			logger.error("Get all Books operation process failed in Bookstore-DB . Exception:" + e.getMessage());
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error"));
		}
	}
	
	

	@Operation(summary = "Get Book By Id", description = "e commerece online books store, get book by Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "book retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "books retrieve operation failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/getCustomerBookByID/{id}")
	public ResponseEntity<ResponseMessage> getByBookId(@PathVariable Long id) {
		logger.info("Get Book by ID controller layer calling or started");
		try {
			BooksEntity byCustmerBookid = booksService.getByCustmerBookid(id);
			if (byCustmerBookid != null) {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_GET_BOOK_BY_ID_SUCCESS\" .");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"custmer Book Id getting successfully", byCustmerBookid));
			} else {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_GET_BOOK_BY_ID_OPERATION_FAILED\" .");
				logger.info("Get Book By ID controller layer calling completed");
				logger.warn("Book service return null : get book by id operation failed");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"custmer Book Id getting  failed", byCustmerBookid));
			}

		} catch (Exception e) {
			logger.error("Get Boo By ID, operation process failed in Bookstore-DB . Exception:" + e.getMessage());
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error"));
		}
	}
}

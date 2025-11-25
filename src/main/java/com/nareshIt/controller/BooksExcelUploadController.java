package com.nareshIt.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nareshIt.entity.BooksExcelFileEntity;
import com.nareshIt.model.ResponseMessage;
import com.nareshIt.repository.BooksExcelFileRepository;
import com.nareshIt.service.BooksExcelUploadService;
import com.nareshIt.service.BooksService;
import com.nareshIt.utility.Constants;
import com.nareshIt.utility.Helper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "BooksExcelUploadController", description = "Add bunch of data using EXCEL File.") // swagger annotation
@RestController
@RequestMapping("/api")
public class BooksExcelUploadController {

	@Autowired
	BooksExcelUploadService booksExcelUploadService;

	@Autowired
	BooksExcelFileRepository repo;

	@Operation(summary = "uploadExcelFile", description = "e commerece online books store, upload excel file")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "saved successfully"),
			@ApiResponse(responseCode = "400", description = "saved failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/uploadExcelFile")
	public ResponseEntity<ResponseMessage> uploadExcelFile(@RequestParam MultipartFile file) throws IOException {

		if (Helper.checkExcelfile(file)) {
			booksExcelUploadService.uploadExcelintoDB(file);
			return ResponseEntity.ok(
					new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Excel file save successfully"));

		} else {

			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,
					"Excel file save failed"));

		}

	}

	@Operation(summary = "Get Product Details", description = "e commerece online books store, Get Product Details")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Get Product Details retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Get Product Details retrieve operation failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("getProductDetails")
	public List<BooksExcelFileEntity> readProductDetails() {

		return repo.findAll();
	}

}

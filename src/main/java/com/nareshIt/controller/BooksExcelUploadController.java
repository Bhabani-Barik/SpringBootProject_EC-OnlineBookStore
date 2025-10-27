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
import com.nareshIt.utility.Constants;
import com.nareshIt.utility.Helper;

@RestController
@RequestMapping("/api")
public class BooksExcelUploadController {
	
	@Autowired
	BooksExcelUploadService booksExcelUploadService;
	
	@Autowired BooksExcelFileRepository repo;

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
	
	@GetMapping("getProductDetails")
	public List<BooksExcelFileEntity> readProductDetails(){
		
		
		return repo.findAll();
	}

}

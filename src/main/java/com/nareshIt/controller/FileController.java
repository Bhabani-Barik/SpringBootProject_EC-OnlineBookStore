package com.nareshIt.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nareshIt.service.IFileManagementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "FileController ", description = "FileController upload single and multiple file") // swagger annotation
@RestController
@RequestMapping("api")
public class FileController {

	@Autowired
	IFileManagementService fileService;

	@Operation(summary = "Add new file", description = "Insert a new file into the database")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "File created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid file data"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/uploadfiles")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {

		String response = fileService.storeFile(file);

		return ResponseEntity.ok(response);

	}

	@Operation(summary = "Add multiple files", description = "Insert multiple  file into the database")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "File created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid file data"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/uploadFileMultiplefiles")
	public ResponseEntity<List<Object>> uploadFileMultiplefiles(@RequestParam MultipartFile[] files)
			throws IOException {

		List<Object> response = Arrays.stream(files).map(s -> {
			try {
				return uploadFile(s);
			} catch (Exception e) {
				return "files upload failed" + e.getLocalizedMessage();
			}
		}).collect(Collectors.toList());
		return ResponseEntity.ok(response);

	}

}

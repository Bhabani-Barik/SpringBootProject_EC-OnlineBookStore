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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nareshIt.entity.UserRegister;
import com.nareshIt.model.ResponseMessage;
import com.nareshIt.model.UserRequest;
import com.nareshIt.model.UserRequestDto;
import com.nareshIt.service.UserRegisterService;
import com.nareshIt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserRegisterController ", description = "UserRegister Regsiter and Login") // swagger annotation
@RestController
@RequestMapping("api")
public class UserRegisterController {
	
	
	// Logger
	private static final Logger logger = LoggerFactory.getLogger(UserRegisterController.class);

	@Autowired
	UserRegisterService userRegisterService;

	@Operation(summary = "Create User Register", description = "e commerece online books store  register the users")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "user register successfully"),
			@ApiResponse(responseCode = "400", description = "user register failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/userregisters")
	public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody UserRequestDto userRequestDto) {
		logger.info("Registration controller layer calling or started");
		try {
			// validation
			// Negative Case
			if (userRequestDto.getEmail() == null || userRequestDto.getEmail().isEmpty()
					|| userRequestDto.getPassword() == null || userRequestDto.getPassword().isEmpty()) {

				logger.debug("Recived userRegData: {} ", userRequestDto);
				logger.warn("missing email and password registration request");
				logger.error("User Registration email or password missing : Bad reg data ");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and passowrd cannot be empty"));
			}

			UserRegister userRegister = userRegisterService.insertUserRegister(userRequestDto);

			// Positive Case
			if (userRegister != null) {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_REGISTRATION_CREATION_SUCCESS\" .");
				// return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,
				// Constants.SUCCESS, "online bookstore save successfully", userRegister));
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"User registered successfully", userRegister));
			} else {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_REGISTRATION_CREATION_FAILED\" .");
				logger.info("Registration controller layer calling completed");
				logger.warn("User Registration service return null : registration failed");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Register Failed", userRegister));

			}
		} catch (Exception e) {
			logger.error("New user creation process failed in Bookstore-DB . Exception:" + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}

	}

	@Operation(summary = "User Login", description = "e commerece online books store  login the users")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "user Login successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Credentials"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/userlogin")
	public ResponseEntity<ResponseMessage> checkLogin(@RequestBody UserRequestDto userRequestDto) {
		logger.info("Login controller layer calling or started");

		try {
			if (userRequestDto.getEmail() == null || userRequestDto.getEmail().isEmpty()
					|| userRequestDto.getPassword() == null || userRequestDto.getPassword().isEmpty()) {
				
				logger.debug("Recived userLoginData: {} ", userRequestDto);
				logger.warn("missing email and password login request");
				logger.error("User Login email or password missing : Bad login credential data ");

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"email and passowrd cannot be empty"));

			}
			
			UserRegister checkUserDetails = userRegisterService.checkUserDetails(userRequestDto);
			
			if (checkUserDetails != null) {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_LOGIN_SUCCESS\" .");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Login successfully", checkUserDetails));

			} else {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_LOGIN_FAILED\" .");
				logger.info("Login controller layer calling completed");
				logger.warn("User Login service return null : login failed");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Invalid creditials...!"));

			}
		} catch (Exception e) {
			logger.error("Login failed in Bookstore-DB . Exception:" + e.getMessage());
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error"));

		}
	}

	@Operation(summary = "User Details", description = "Get the user deatils from EC-Online Book Store Management Application")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Getting user details successfully")})
	@GetMapping("/getUserDetails/{id}")
	public UserRequest getUserRegisterDetailsById (@PathVariable Long id) {
		logger.info("getUserRegisterDetailsById controller layer calling or started");
		UserRequest registerDetails = userRegisterService.getUserRegisterDetails(id);
		logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_GET_USER_DETAILS_BY_ID_SUCCESS\" .");
		return registerDetails;
	}
	
	
	
	@Operation(summary = "Create User Register and upload multiple images", description = "e commerece online books store  register the users and upload multiple images")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "user register and image upload successfully"),
			@ApiResponse(responseCode = "400", description = "user register and image upload failed"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/uploadMultiUserRegister")
	public ResponseEntity<ResponseMessage> uploadMultiUserRegister(@RequestParam String jsonData,
			MultipartFile[] files) {
		
		logger.info("uploadMultiUserRegister controller layer calling or started");
		try {

			UserRequestDto userRequestDto = new ObjectMapper().readValue(jsonData, UserRequestDto.class);

			UserRegister uploadMultiUserRegister = userRegisterService.uploadMultiUserRegister(userRequestDto, files);

			if (uploadMultiUserRegister != null) {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_UPLOAD_MULTI_USER_REGISTER_SUCCESS\" .");
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"user registered succesfully", uploadMultiUserRegister));

			} else {
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_UPLOAD_MULTI_USER_REGISTER_FAILED\" .");
				logger.info("uploadMultiUserRegister controller layer calling completed");
				logger.warn("uploadMultiUserRegister service return null : registration & file upload failed");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "user registered failed", uploadMultiUserRegister));
			}
		} catch (Exception e) {
			logger.error("New user creation & file upload process failed in Bookstore-DB . Exception:" + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal Server Error"));

		}

	}
	
	@Operation(summary = "Get all user details", description = "e commerece online books store, get all users data")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "user details fetched successfully"),
			@ApiResponse(responseCode = "400", description = "user details fetched failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/getAllUsers")
	public List<UserRegister> getAllUserDetalsData() {
		logger.info("getAllUserDetalsData controller layer calling or started");
		List<UserRegister> allUsersRegisterDetails = userRegisterService.getAllUsersRegisterDetails();
		logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_GET_ALL_USER_DETAILS_SUCCESS\" .");
		return allUsersRegisterDetails;

	}

}

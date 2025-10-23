package com.nareshIt.exceptions;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.nareshIt.SpringBootProjectEcOnlineBookStoreApplication;
import com.nareshIt.model.ErrorResponseMessage;
import com.nareshIt.utility.Constants;

@ControllerAdvice
public class RestGlobalExceptionHandle {

    private final SpringBootProjectEcOnlineBookStoreApplication springBootProjectEcOnlineBookStoreApplication;


    RestGlobalExceptionHandle(SpringBootProjectEcOnlineBookStoreApplication springBootProjectEcOnlineBookStoreApplication) {
        this.springBootProjectEcOnlineBookStoreApplication = springBootProjectEcOnlineBookStoreApplication;
    }
	
	
	// ArrayList Way
//	@ExceptionHandler(CustomerIDNotFoundException.class)
//	public ResponseEntity<Object> custmerHandleException(CustomerIDNotFoundException ex) {
//		List<String> details = new ArrayList<>();
//		details.add("Error : Customer Id not found");
//		details.add("Detailed Message: " + ex.getLocalizedMessage());
//		details.add("Timestamp:" + System.currentTimeMillis());
//		ErrorResponseMessage error = new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,
//				"Customer ID Not Found!", details);
//		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	}
	
	
	// HashMap Way
//	@ExceptionHandler(CustomerIDNotFoundException.class)
//	public ResponseEntity<Object> custmerHandleException(CustomerIDNotFoundException ex) {
//		
//		Map<String, Object> details = new HashMap<>();
//		details.put("Error :", "Customer Id not found");
//		details.put("Detailed Message:" , ex.getLocalizedMessage());
//		details.put("Timestamp:" ,System.currentTimeMillis());
//		ErrorResponseMessage error = new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "Customer ID Not Found",details);
//		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//
//	}

	
//	@ExceptionHandler(InternalServerException.class)
//	public ResponseEntity<Object> internalServerError(InternalServerException ex){
//		Map<String, Object> map = new HashMap<>();
//		map.put("Error Message : ", "Internal Server Error");
//		map.put("Detailed Message : ", ex.getLocalizedMessage());
//		map.put("TimeStamp: ",System.currentTimeMillis());
//		
//		ErrorResponseMessage error = new ErrorResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal Server Error", map);
//		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	
//	@ExceptionHandler(CustomerNotFoundException.class)
//	public ResponseEntity<Object> customerNotFoundException(CustomerNotFoundException ex) {
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("Error Message: ", "Customer Not Found");
//		map.put("Detailed Message : " , ex.getLocalizedMessage());
//  map.put("TimeStamp: ", System.currentTimeMillis());
//		
//		ErrorResponseMessage error = new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Customer Not Found", map);
//		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	}
	
	
	@ExceptionHandler(CustomerIDNotFoundException.class)
	public ResponseEntity<ErrorResponseMessage> customerIdNotFoundException(CustomerIDNotFoundException ex) {

		List<String> list = new ArrayList<>();

		list.add("Error: CustomerIDNotFoundException");
		list.add("Error message: " + ex.getLocalizedMessage());
		list.add("Timestamp: " + new Date(System.currentTimeMillis()));

		ErrorResponseMessage err = new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
				"Customer ID Not Found", list);

		return ResponseEntity.ok(err);

	}
	
	
	
	

}

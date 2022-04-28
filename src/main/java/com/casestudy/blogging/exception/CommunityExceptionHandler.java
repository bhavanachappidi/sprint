package com.casestudy.blogging.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.casestudy.blogging.bean.CommunityErrorResponse;

@ControllerAdvice
public class CommunityExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<CommunityErrorResponse> handleException(CommunityNotFoundException exception)
	{
		//Create ErrorResponse Object
		CommunityErrorResponse error = new CommunityErrorResponse();
		//Set error Status,Message,TimeStamp
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}

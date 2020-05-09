package com.megastatics.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.megastatics.services.exception.DataIntegrityException;
import com.megastatics.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourcExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandartError error = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandartError> dataIntegratyNotFound(DataIntegrityException e, HttpServletRequest request){
		StandartError error = new StandartError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}

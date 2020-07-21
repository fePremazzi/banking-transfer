package com.cvc.banking_transfer.api.exceptionhandler;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cvc.banking_transfer.domain.exception.DomainException;
import com.cvc.banking_transfer.domain.exception.TaxNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(TaxNotFoundException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(DomainException ex, WebRequest web) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseException response = new ResponseException();
		response.setStatus(status.value());
		response.setDescription(ex.getMessage());
		response.setDateTime(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, web);
		
	}

}

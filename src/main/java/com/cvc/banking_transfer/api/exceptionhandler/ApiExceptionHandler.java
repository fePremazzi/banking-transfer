package com.cvc.banking_transfer.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cvc.banking_transfer.domain.exception.DomainException;
import com.cvc.banking_transfer.domain.exception.TaxNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(TaxNotFoundException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(DomainException ex, WebRequest web) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseException response = new ResponseException();
		response.setStatus(status.value());
		response.setDescription(ex.getMessage());
		response.setDateTime(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, web);
		
	}
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleNegocio(DomainException ex, WebRequest web) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseException response = new ResponseException();
		response.setStatus(status.value());
		response.setDescription(ex.getMessage());
		response.setDateTime(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, web);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ArrayList<Field> campos = new ArrayList<>();		
		ResponseException problema = new ResponseException();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String msg = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Field(nome, msg));
		}
		
		problema.setStatus(status.value());
		problema.setDescription("One or more fields are invalid.");
		problema.setDateTime(OffsetDateTime.now());
		problema.setFields(campos);
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}


}

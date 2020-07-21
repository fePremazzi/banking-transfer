package com.cvc.banking_transfer.domain.exception;

public class DomainException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DomainException(String message) {
		super(message);
	}

}

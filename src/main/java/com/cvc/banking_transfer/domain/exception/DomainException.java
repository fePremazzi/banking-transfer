package com.cvc.banking_transfer.domain.exception;

/**
 * Generic exception due to some domain error
 * @author felli
 *
 */
public class DomainException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DomainException(String message) {
		super(message);
	}

}

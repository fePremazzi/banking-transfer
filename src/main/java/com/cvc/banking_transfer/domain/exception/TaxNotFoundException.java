package com.cvc.banking_transfer.domain.exception;

/**
 * Exception when a tax is not applicable due to invalid inputs from the View.
 * @author felli
 *
 */
public class TaxNotFoundException extends DomainException{

	public TaxNotFoundException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;
	
	

}

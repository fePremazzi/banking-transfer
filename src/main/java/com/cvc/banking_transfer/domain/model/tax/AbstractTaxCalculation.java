package com.cvc.banking_transfer.domain.model.tax;

import java.math.BigDecimal;

import com.cvc.banking_transfer.domain.model.Transfer;

public abstract class AbstractTaxCalculation implements TaxCalculation {
	
	protected TaxCalculation nextTax;

	@Override
	public BigDecimal calculate(Transfer transfer) {
		if (isTaxRight(transfer)) {
			return calculateTax(transfer);
		}
		return nextTax.calculate(transfer);
	}

	@Override
	public void setNext(TaxCalculation nextTax) {
		this.nextTax = nextTax;
		
	}	
}

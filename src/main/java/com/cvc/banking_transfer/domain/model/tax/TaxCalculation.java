package com.cvc.banking_transfer.domain.model.tax;

import java.math.BigDecimal;

import com.cvc.banking_transfer.domain.model.Transfer;

public interface TaxCalculation {
	

	BigDecimal calculate (Transfer transfer);
	void setNext(TaxCalculation nextTax);
	boolean isTaxRight(Transfer transfer);
	BigDecimal calculateTax(Transfer transfer);

}

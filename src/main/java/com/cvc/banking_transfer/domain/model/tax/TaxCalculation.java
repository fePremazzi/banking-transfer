package com.cvc.banking_transfer.domain.model.tax;

import java.math.BigDecimal;

import com.cvc.banking_transfer.domain.model.Transfer;

public interface TaxCalculation {
	
	public BigDecimal calculate (Transfer transfer);

}

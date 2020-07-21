package com.cvc.banking_transfer.domain.model.tax;

import java.math.BigDecimal;

import com.cvc.banking_transfer.domain.exception.TaxNotFoundException;
import com.cvc.banking_transfer.domain.model.Transfer;

public class DefaultTax extends AbstractTaxCalculation{

	@Override
	public boolean isTaxRight(Transfer transfer) {
		return true;
	}

	@Override
	public BigDecimal calculateTax(Transfer transfer) {
		throw new TaxNotFoundException("Tax not found for the date and value provided");
	}

}

package com.cvc.banking_transfer.domain.model.tax;

import java.math.BigDecimal;

import com.cvc.banking_transfer.domain.model.Transfer;

public class ATax implements TaxCalculation{

	@Override
	public BigDecimal calculate(Transfer transfer) {
		return new BigDecimal(3).add(transfer.getValue().multiply(new BigDecimal(0.03)));
	}

}

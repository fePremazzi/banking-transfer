package com.cvc.banking_transfer.domain.model.tax;

import java.math.BigDecimal;
import java.time.Duration;

import com.cvc.banking_transfer.domain.model.Transfer;

public class BTax implements TaxCalculation{

	@Override
	public BigDecimal calculate(Transfer transfer) {
		long days = Duration.between(transfer.getOpeningDate(), transfer.getScheduledDate()).toDays();
		return new BigDecimal(12).multiply(BigDecimal.valueOf(days));
	}
}

package com.cvc.banking_transfer.domain.model.tax;

import java.math.BigDecimal;
import java.time.Duration;

import com.cvc.banking_transfer.domain.exception.TaxNotFoundException;
import com.cvc.banking_transfer.domain.model.Transfer;

public class CTax implements TaxCalculation {

	@Override
	public BigDecimal calculate(Transfer transfer) {
		long days = Duration.between(transfer.getOpeningDate(), transfer.getScheduledDate()).toDays();
		BigDecimal transferValue = transfer.getValue();

		if (days > 10 && days <= 20) {
			return BigDecimal.valueOf(0.08).multiply(transferValue);
		} else if (days <= 30) {
			return BigDecimal.valueOf(0.06).multiply(transferValue);
		} else if (days <= 40) {
			return BigDecimal.valueOf(0.04).multiply(transferValue);
		} else if (days > 40 && transferValue.compareTo(BigDecimal.valueOf(10000)) == 0) {
			return BigDecimal.valueOf(0.02).multiply(transferValue);
		}
		
		throw new TaxNotFoundException("Tax not found for the date and value provided");
	}

}

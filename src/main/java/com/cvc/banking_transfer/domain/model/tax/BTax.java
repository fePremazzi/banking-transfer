package com.cvc.banking_transfer.domain.model.tax;

import java.math.BigDecimal;
import java.time.Duration;

import com.cvc.banking_transfer.domain.model.Transfer;

public class BTax extends AbstractTaxCalculation{

	public BTax() {
		setNext(new CTax());
	}

	@Override
	public boolean isTaxRight(Transfer transfer) {
		long days = Duration.between(transfer.getOpeningDate(), transfer.getScheduledDate()).toDays();
		return days <= 10;
		
	}

	@Override
	public BigDecimal calculateTax(Transfer transfer) {
		transfer.setTaxType(TaxType.B);
		long days = Duration.between(transfer.getOpeningDate(), transfer.getScheduledDate()).toDays();
		return new BigDecimal(12).multiply(BigDecimal.valueOf(days));
	}


}

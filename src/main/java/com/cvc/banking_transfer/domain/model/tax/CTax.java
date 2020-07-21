package com.cvc.banking_transfer.domain.model.tax;

import java.math.BigDecimal;
import java.time.Duration;

import com.cvc.banking_transfer.domain.exception.TaxNotFoundException;
import com.cvc.banking_transfer.domain.model.Transfer;

public class CTax extends AbstractTaxCalculation {
	
	public CTax() {
		setNext(new DefaultTax());
	}

	@Override
	public boolean isTaxRight(Transfer transfer) {
		long days = Duration.between(transfer.getOpeningDate(), transfer.getScheduledDate()).toDays();
		
		return (days <= 40 || 
                (days > 40 && transfer.getValue().compareTo(BigDecimal.valueOf(100000)) == 1));
	}

	@Override
	public BigDecimal calculateTax(Transfer transfer) {
		transfer.setTaxType(TaxType.C);
		
		long days = Duration.between(transfer.getOpeningDate(), transfer.getScheduledDate()).toDays();
		BigDecimal transferValue = transfer.getValue();

		if (days > 10 && days <= 20) {
			return BigDecimal.valueOf(0.08).multiply(transferValue);
		} else if (days <= 30) {
			return BigDecimal.valueOf(0.06).multiply(transferValue);
		} else if (days <= 40) {
			return BigDecimal.valueOf(0.04).multiply(transferValue);
		} else if (days > 40 && transferValue.compareTo(BigDecimal.valueOf(100000)) == 1) {
			return BigDecimal.valueOf(0.02).multiply(transferValue);
		}
		return BigDecimal.ZERO;
	}

}

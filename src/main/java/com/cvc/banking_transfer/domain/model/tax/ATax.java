package com.cvc.banking_transfer.domain.model.tax;

import java.math.BigDecimal;
import java.time.Duration;

import org.springframework.stereotype.Component;

import com.cvc.banking_transfer.domain.model.Transfer;

@Component
public class ATax extends AbstractTaxCalculation{

	public ATax() {
		setNext(new BTax());
	}

	@Override
	public boolean isTaxRight(Transfer transfer) {
		long days = Duration.between(transfer.getOpeningDate(), transfer.getScheduledDate()).toDays();
		return days == 0 ;
	}

	@Override
	public BigDecimal calculateTax(Transfer transfer) {
		transfer.setTaxType(TaxType.A);
		return new BigDecimal(3).add(transfer.getValue().multiply(new BigDecimal(0.03)));
	}

	

}

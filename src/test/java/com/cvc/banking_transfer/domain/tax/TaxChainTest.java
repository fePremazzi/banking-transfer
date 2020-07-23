package com.cvc.banking_transfer.domain.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cvc.banking_transfer.domain.exception.TaxNotFoundException;
import com.cvc.banking_transfer.domain.model.Transfer;
import com.cvc.banking_transfer.domain.model.tax.ATax;
import com.cvc.banking_transfer.domain.model.tax.TaxCalculation;
import com.cvc.banking_transfer.domain.model.tax.TaxType;

public class TaxChainTest {

	private Transfer transfer;

	private TaxCalculation taxCalculation = new ATax();

	@BeforeEach
	public void setup() {
		transfer = new Transfer();
		transfer.setOpeningDate(LocalDate.now());
		transfer.setValue(BigDecimal.valueOf(100));
	}

	@Test
	public void aTaxTest() {
		transfer.setScheduledDate(LocalDate.now());
		taxCalculation.calculate(transfer);

		assertEquals(TaxType.A, transfer.getTaxType());
	}

	@Test
	public void bTaxTest() {
		transfer.setScheduledDate(LocalDate.now().plusDays(5));
		taxCalculation.calculate(transfer);
		assertEquals(TaxType.B, transfer.getTaxType());
	}

	@Test
	public void cTaxTest() {
		transfer.setScheduledDate(LocalDate.now().plusDays(20));
		taxCalculation.calculate(transfer);
		assertEquals(TaxType.C, transfer.getTaxType());
	}

	@Test
	public void notFoundTaxTest() throws Exception {
		transfer.setScheduledDate(LocalDate.now().plusDays(41));
		assertThrows(TaxNotFoundException.class, () -> taxCalculation.calculate(transfer),
				"Tax not found for the date and value provided");

	}

}

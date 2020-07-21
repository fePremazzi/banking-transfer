package com.cvc.banking_transfer.domain.service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvc.banking_transfer.domain.model.Transfer;
import com.cvc.banking_transfer.domain.model.tax.TaxType;
import com.cvc.banking_transfer.domain.repository.TransferRepository;

@Service
public class TransferServiceImpl implements TransferServiceInterface {

	@Autowired
	private TransferRepository transferRepository;

	@Override
	public Transfer save(Transfer transfer) {

		transfer.setOpeningDate(OffsetDateTime.now());
		resolveTax(transfer);

		return transferRepository.save(transfer);
	}

	@Override
	public List<Transfer> findAll() {
		return transferRepository.findAll();
	}
	

	@Override
	public void delete(Long transferId) {
		transferRepository.deleteById(transferId);
	}

	@Override
	public Optional<Transfer> findByTransferId(Long transferId) {
		return transferRepository.findById(transferId);
	}


	private void resolveTax(Transfer transfer) {

		long days = Duration.between(transfer.getOpeningDate(), transfer.getScheduledDate()).toDays();

		if (days == 0) {
			transfer.setTaxType(TaxType.A);
		} else if (days <= 10) {
			transfer.setTaxType(TaxType.B);
		} else {
			transfer.setTaxType(TaxType.C);
		}

	}

}

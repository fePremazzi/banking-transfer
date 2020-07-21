package com.cvc.banking_transfer.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvc.banking_transfer.domain.model.Transfer;
import com.cvc.banking_transfer.domain.model.tax.TaxCalculation;
import com.cvc.banking_transfer.domain.repository.TransferRepository;

@Service
public class TransferServiceImpl implements TransferServiceInterface {

	@Autowired
	private TransferRepository transferRepository;
	
	@Autowired
	private TaxCalculation taxCalculation;

	@Override
	public Transfer save(Transfer transfer) {

		transfer.setOpeningDate(OffsetDateTime.now());
		transfer.setTaxValue(taxCalculation.calculate(transfer));

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

}
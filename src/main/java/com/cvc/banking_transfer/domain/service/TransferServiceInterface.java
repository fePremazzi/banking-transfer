package com.cvc.banking_transfer.domain.service;

import java.util.List;
import java.util.Optional;

import com.cvc.banking_transfer.domain.model.Transfer;

public interface TransferServiceInterface {
	
	Transfer save(Transfer transfer);
	List<Transfer> findAll();
	void delete (Long transferId);
	Optional<Transfer> findByTransferId(Long transferId);

}

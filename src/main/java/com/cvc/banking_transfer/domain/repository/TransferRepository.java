package com.cvc.banking_transfer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvc.banking_transfer.domain.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

}

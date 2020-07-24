package com.cvc.banking_transfer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvc.banking_transfer.domain.model.Transfer;

/**
 * Repository layer to implement {@link JpaRepository} to persist {@link Transfer}
 * objects to the database.
 * @author felli
 *
 */
public interface TransferRepository extends JpaRepository<Transfer, Long> {

}

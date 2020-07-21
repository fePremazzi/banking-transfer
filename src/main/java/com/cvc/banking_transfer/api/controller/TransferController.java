package com.cvc.banking_transfer.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cvc.banking_transfer.api.model.TransferInput;
import com.cvc.banking_transfer.api.model.TransferOutput;
import com.cvc.banking_transfer.domain.model.Transfer;
import com.cvc.banking_transfer.domain.service.TransferServiceImpl;

@RestController
@RequestMapping("/schedules")
public class TransferController {

	@Autowired
	private TransferServiceImpl transferServiceImpl;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TransferOutput create(@Valid @RequestBody TransferInput transferInput) {

		Transfer transfer = toDomainModel(transferInput);

		return toOutput(transferServiceImpl.save(transfer));
	}

	@GetMapping
	public List<Transfer> findAll() {
		return transferServiceImpl.findAll();
	}

	@GetMapping("/{transferId}")
	public ResponseEntity<TransferOutput> find(@PathVariable Long transferId) {
		Optional<Transfer> transfer = transferServiceImpl.findByTransferId(transferId);

		if (transfer.isPresent()) {
			TransferOutput transferOutput = toOutput(transfer.get());
			return ResponseEntity.ok(transferOutput);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{transferId}")
	public ResponseEntity<Void> delete(@PathVariable Long transferId) {
		if (!transferServiceImpl.existsById(transferId)) {
			return ResponseEntity.notFound().build();
		}
		transferServiceImpl.delete(transferId);
		return ResponseEntity.noContent().build();

	}
	
	@PutMapping("/{transferId}")
	public ResponseEntity<TransferOutput> update(@PathVariable Long transferId, 
			@Valid @RequestBody TransferInput transferInput){
		
		if(!transferServiceImpl.existsById(transferId)) {
			return ResponseEntity.notFound().build();
		}
		transferInput.setId(transferId);
		Transfer transfer = toDomainModel(transferInput);
		
		return ResponseEntity.ok(toOutput(transferServiceImpl.save(transfer)));
	}

	private Transfer toDomainModel(TransferInput transferInput) {
		return modelMapper.map(transferInput, Transfer.class);
	}

	private TransferOutput toOutput(Transfer transfer) {
		return modelMapper.map(transfer, TransferOutput.class);
	}

}

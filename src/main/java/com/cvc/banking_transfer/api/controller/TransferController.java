package com.cvc.banking_transfer.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cvc.banking_transfer.api.model.TransferInput;
import com.cvc.banking_transfer.api.model.TransferOutput;
import com.cvc.banking_transfer.domain.model.Transfer;
import com.cvc.banking_transfer.domain.service.TransferServiceImpl;

@RestController
@RequestMapping("/transfers")
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
	
	private Transfer toDomainModel (TransferInput transferInput) {
		return modelMapper.map(transferInput, Transfer.class);
	}
	
	private TransferOutput toOutput (Transfer transfer) {
		return modelMapper.map(transfer, TransferOutput.class);
	}
	

}

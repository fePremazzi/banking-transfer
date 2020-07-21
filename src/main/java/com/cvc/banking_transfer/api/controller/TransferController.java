package com.cvc.banking_transfer.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvc.banking_transfer.domain.service.TransferServiceImpl;

@RestController
@RequestMapping("/transfers")
public class TransferController {
	
	@Autowired
	private TransferServiceImpl transferServiceImpl;
	
	@Autowired
	private ModelMapper modelMapper;

}

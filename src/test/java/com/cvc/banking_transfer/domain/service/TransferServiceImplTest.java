package com.cvc.banking_transfer.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cvc.banking_transfer.domain.model.Transfer;
import com.cvc.banking_transfer.domain.model.tax.TaxCalculation;
import com.cvc.banking_transfer.domain.model.tax.TaxType;
import com.cvc.banking_transfer.domain.repository.TransferRepository;


@ExtendWith(MockitoExtension.class)
public class TransferServiceImplTest {

	@Mock
	TransferRepository transferRepository;
	
	@Mock
	@Resource
	TaxCalculation taxCalculation;

	@InjectMocks
	TransferServiceImpl  transferServiceImpl;
	
	Transfer transfer;
	
	
	@BeforeEach
    public void setup() {
		transfer = new Transfer("123456", "456789", BigDecimal.valueOf(10000), LocalDate.now(), LocalDate.now());
		transfer.setId(1L);
		transfer.setTaxType(TaxType.A);
		transfer.setTaxValue(BigDecimal.valueOf(303));
    }
	
	@Test
	void saveTest() {
		when(transferRepository.save(any())).thenReturn(transfer);
        Transfer transferToBeSaved = transferRepository.save(transfer);
        assertEquals(transferToBeSaved, transfer);
	
	}
	
	@Test
    public void findaAllTest() {
        when(transferRepository.findAll()).thenReturn(List.of(transfer));
        List<Transfer> transfers = transferServiceImpl.findAll();
        assertEquals(List.of(transfer), transfers);
    }
	
	@Test
    public void findByIdTest() {
        when(transferRepository.findById(any())).thenReturn(Optional.of(transfer));
        
        Transfer transferReturned = transferServiceImpl.findByTransferId(transfer.getId()).get();
        assertEquals(transfer, transferReturned);
    }
	
	@Test
    public void deleteTest() {
        doNothing().when(transferRepository).deleteById(any());
        transferServiceImpl.delete(transfer.getId());
        verify(transferRepository, times(1)).deleteById(any());
    }

}


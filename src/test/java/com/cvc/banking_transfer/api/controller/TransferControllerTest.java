package com.cvc.banking_transfer.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.cvc.banking_transfer.api.model.TransferInput;
import com.cvc.banking_transfer.api.model.TransferOutput;
import com.cvc.banking_transfer.domain.model.Transfer;
import com.cvc.banking_transfer.domain.model.tax.TaxCalculation;
import com.cvc.banking_transfer.domain.repository.TransferRepository;
import com.cvc.banking_transfer.domain.service.TransferServiceImpl;


@WebAppConfiguration
@ContextConfiguration(classes = {TransferRepository.class})
public class TransferControllerTest {
	
	@InjectMocks
    TransferController transferController = new TransferController();

    @Mock
    TransferServiceImpl transferServiceImpl = new TransferServiceImpl();
    
    @Mock
    TaxCalculation taxCalculation;

    @Mock
    View mockView;

    @Mock
    ModelMapper modelMapper = new ModelMapper();
    
    Transfer transfer;

    TransferOutput transferOutput;
    TransferInput transferInput;
    
    MockMvc mvc;
        
    @BeforeEach
    public void setup() {
        transfer = new Transfer();
        transferOutput = new TransferOutput();
        transferInput = new TransferInput();
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(transferController).setSingleView(mockView).build();
    }
    
    @Test
    public void saveTest() throws Exception {
        MockHttpServletRequestBuilder builder = post("/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n" + 
                		"	\"originAccount\":\"123456\",\r\n" + 
                		"	\"destinationAccount\":\"456789\",\r\n" + 
                		"	\"value\":10000,\r\n" + 
                		"	\"scheduledDate\":\"2020-07-22\"\r\n" + 
                		"}");
        mvc.perform(builder).andExpect(status().isCreated());
    }
    
    @Test
    public void listAllTest() throws Exception {
        List<Transfer> expectedTransfers = List.of(transfer);

        when(transferServiceImpl.findAll()).thenReturn(expectedTransfers);
        when(modelMapper.map(transfer, TransferOutput.class)).thenReturn(transferOutput);

        mvc.perform(get("/schedules")).andExpect(status().isOk());
    }
    
    @Test
    public void getTest() throws Exception {
    	when(modelMapper.map(transfer, TransferOutput.class)).thenReturn(transferOutput);
        mvc.perform(get("/schedules").param("transferId", "1")).andExpect(status().isOk());
    }

    @Test
    public void deleteNotFoundTest() throws Exception {
        mvc.perform(delete("/schedules/{transferId}", "11")).andExpect(status().isNotFound());
    }



}

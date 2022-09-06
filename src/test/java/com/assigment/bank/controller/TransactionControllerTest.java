package com.assigment.bank.controller;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.service.implServices.MinusTransactionServiceImpl;
import com.assigment.bank.service.implServices.PlusTransactionServiceImpl;
import com.assigment.bank.service.implServices.TransferTransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlusTransactionServiceImpl toUpService;
    @MockBean
    private MinusTransactionServiceImpl withdrawService;
    @MockBean
    private TransferTransactionServiceImpl transactionService;

    private TransactionDto baseTransactionDto;
    private TransactionDto transferTransactionDto;

    @Before
    public void init() {
        baseTransactionDto = TransactionDto.builder()
                .accountNumber(1L)
                .txAmount(BigDecimal.valueOf(10000))
                .txType("type")
                .build();

        transferTransactionDto = TransactionDto.builder()
                .accountNumber(1L)
                .toAccountNumber(2L)
                .txAmount(BigDecimal.valueOf(10000))
                .txType("type")
                .build();
    }

    @Test
    public void plusTransaction() throws Exception {
        String response = "You have topped up your balance on 10000";

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/transaction/toUp")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJson(baseTransactionDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(response));
    }

    @Test
    public void minusTransaction() throws Exception {
        String response = "You have withdrawn 10000 from your balance!";

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/transaction/withdraw")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJson(baseTransactionDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(response));
    }

    @Test
    public void transferTransaction() throws Exception {
        String response = "The transaction from account 1 to account 2 was successful!";
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/transaction/transfer")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJson(transferTransactionDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(response));
    }
}
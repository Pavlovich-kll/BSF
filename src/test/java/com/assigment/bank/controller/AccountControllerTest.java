package com.assigment.bank.controller;

import com.assigment.bank.dto.AccountDto;
import com.assigment.bank.service.implServices.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountServiceImpl accountService;

    private AccountDto dto;

    @Before
    public void init() {
        dto = AccountDto.builder()
                .accountBalance(BigDecimal.valueOf(10000))
                .accountStatus("enabled")
                .accountType("Dollar")
                .userNumber(1L)
                .build();
    }

    @Test
    public void createAccount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJson(dto)))
                .andExpect(status().isCreated());
    }
}
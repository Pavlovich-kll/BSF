package com.assigment.bank.controller;

import com.assigment.bank.repository.BankRepository;
import com.assigment.bank.repository.ContactRepository;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AbstractControllerTest {

    @MockBean
    SavingsAccountRepository savingsAccountRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    ContactRepository contactRepository;
    @MockBean
    TransactionRepository transactionRepository;
    @MockBean
    BankRepository bankRepository;

    protected String toJson(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}

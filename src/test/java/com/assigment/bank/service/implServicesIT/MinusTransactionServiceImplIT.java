package com.assigment.bank.service.implServicesIT;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.repositoryIT.Initializer.ContainerDB;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import com.assigment.bank.service.implServices.MinusTransactionServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.math.BigDecimal;
import java.util.List;

@SqlGroup({
        @Sql("/init-test.sql")
})
public class MinusTransactionServiceImplIT extends ContainerDB {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private SavingsAccountRepository accountRepository;
    @Autowired
    private ModelToEntityConverter toEntityConverter;

    @Autowired
    private MinusTransactionServiceImpl service;

    /**
     * User has account and enough money for the minus transaction
     */
    @Test
    public void doOperation() {
        TransactionDto transaction = TransactionDto.builder()
                .accountNumber(1L)
                .txAmount(BigDecimal.valueOf(10000))
                .txType("withdraw")
                .build();

        service.doOperation(transaction);
        List<TransactionEntity> all = transactionRepository.findAll();
        Assert.assertEquals(1, all.size());
    }
}
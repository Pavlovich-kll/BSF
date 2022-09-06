package com.assigment.bank.service.implServicesIT;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.repositoryIT.Initializer.ContainerDB;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import com.assigment.bank.service.implServices.TransferTransactionServiceImpl;
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
public class TransferTransactionServiceImplTest extends ContainerDB {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private SavingsAccountRepository accountRepository;
    @Autowired
    private ModelToEntityConverter toEntityConverter;

    @Autowired
    private TransferTransactionServiceImpl service;

    /**
     * User has enough money on account to transfer to another user
     */
    @Test
    public void doTransfer() {
        TransactionDto transaction = TransactionDto.builder()
                .accountNumber(1L)
                .toAccountNumber(2L)
                .txAmount(BigDecimal.valueOf(1000))
                .txType("transfer")
                .build();

        service.doTransfer(transaction, 2L);
        List<TransactionEntity> allTransactions = transactionRepository.findAll();
        //this operation has two transactions for the first user and for the second
        Assert.assertEquals(2, allTransactions.size());
    }
}
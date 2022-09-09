package com.assigment.bank.service.implServices;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.exceptionHandler.exception.AccountBalanceException;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Implementation of a money transaction to withdraw
 */
@Slf4j
@Service
public class MinusTransactionServiceImpl extends AbstractTransactionService {

    @Autowired
    protected MinusTransactionServiceImpl(TransactionRepository transactionRepository
            , SavingsAccountRepository accountRepository
            , ModelToEntityConverter toEntityConverter) {
        super(transactionRepository, accountRepository, toEntityConverter);
    }

    @Override
    public void doCalculation(Map<String, AccountEntity> mapAccounts, Map<String, TransactionEntity> mapTransactions, TransactionDto transaction) {
        AccountEntity acc = mapAccounts.get("baseTransactionAcc");
        TransactionEntity transactionMinus = mapTransactions.get("baseTransaction");
        BigDecimal accountBalance = acc.getAccountBalance();
        BigDecimal transactionSum = transaction.getTxAmount();

        if (accountBalance.compareTo(transactionSum) < 0) {
            throw new AccountBalanceException("The account balance is less then minus transaction!");
        } else {
            BigDecimal result = accountBalance.subtract(transactionSum);
            acc.setAccountBalance(result);
            accountRepository.saveAndFlush(acc);
            transactionRepository.saveAndFlush(transactionMinus);
            log.info("You did successfully minus transaction");
        }
    }
}

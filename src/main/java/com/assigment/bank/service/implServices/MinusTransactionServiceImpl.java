package com.assigment.bank.service.implServices;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.service.AbstractTransaction;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Map;

@Service
@Transactional
public class MinusTransactionServiceImpl extends AbstractTransaction {

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
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "The account balance is less then minus transaction!");
        } else {
            BigDecimal result = accountBalance.subtract(transactionSum);
            acc.setAccountBalance(result);
            accountRepository.saveAndFlush(acc);
            transactionRepository.saveAndFlush(transactionMinus);
        }
    }
}

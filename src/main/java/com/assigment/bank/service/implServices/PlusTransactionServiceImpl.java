package com.assigment.bank.service.implServices;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.service.AbstractTransaction;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Service
public class PlusTransactionServiceImpl extends AbstractTransaction {

    @Autowired
    protected PlusTransactionServiceImpl(TransactionRepository transactionRepository
            , SavingsAccountRepository accountRepository
            , ModelToEntityConverter toEntityConverter) {
        super(transactionRepository, accountRepository, toEntityConverter);
    }

    @Override
    @Transactional
    public void doCalculation(Map<String, AccountEntity> mapAccounts, Map<String, TransactionEntity> mapTransactions, TransactionDto transaction) {
        AccountEntity acc = mapAccounts.get("baseTransactionAcc");
        TransactionEntity transactionPlus = mapTransactions.get("baseTransaction");
        BigDecimal result = acc.getAccountBalance().add(transaction.getTxAmount());
        acc.setAccountBalance(result);

        accountRepository.saveAndFlush(acc);
        transactionRepository.saveAndFlush(transactionPlus);
        log.info("You did successfully plus transaction");
    }
}

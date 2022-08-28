package com.assigment.bank.service.implServices;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.service.AbstractTransaction;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void doCalculation(Map<String, AccountEntity> mapAccounts, TransactionDto transaction, SavingsAccountRepository accountRepository) {
        AccountEntity acc = mapAccounts.get("transaction");
        BigDecimal result = acc.getAccountBalance().subtract(transaction.getTxAmount());
        acc.setAccountBalance(result);
        accountRepository.saveAndFlush(acc);
    }
}

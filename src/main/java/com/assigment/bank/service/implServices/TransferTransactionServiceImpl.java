package com.assigment.bank.service.implServices;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.service.AbstractTransaction;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class TransferTransactionServiceImpl extends AbstractTransaction {

    @Autowired
    protected TransferTransactionServiceImpl(TransactionRepository transactionRepository
            , SavingsAccountRepository accountRepository
            , ModelToEntityConverter toEntityConverter) {
        super(transactionRepository, accountRepository, toEntityConverter);
    }

    @Override
    protected void doCalculation(Map<String, AccountEntity> mapAccounts, TransactionDto transaction, SavingsAccountRepository accountRepository) {
        AccountEntity accFrom = mapAccounts.get("transferFrom");
        AccountEntity accTo = mapAccounts.get("transferTo");

        BigDecimal result1 = accFrom.getAccountBalance().subtract(transaction.getTxAmount());
        accFrom.setAccountBalance(result1);
        accountRepository.saveAndFlush(accFrom);

        BigDecimal result2 = accTo.getAccountBalance().add(transaction.getTxAmount());
        accTo.setAccountBalance(result2);
        accountRepository.saveAndFlush(accTo);
    }
}

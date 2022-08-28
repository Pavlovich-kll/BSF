package com.assigment.bank.service;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractTransaction {

    private final TransactionRepository transactionRepository;
    private final SavingsAccountRepository accountRepository;
    private final ModelToEntityConverter toEntityConverter;


    protected AbstractTransaction(TransactionRepository transactionRepository, SavingsAccountRepository accountRepository, ModelToEntityConverter toEntityConverter) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.toEntityConverter = toEntityConverter;
    }

    public void doOperation(TransactionDto transaction) {
        TransactionEntity topUpTransaction = toEntityConverter.convertToTransactionEntity(transaction);
        Optional<AccountEntity> account = accountRepository.findByUserNumber(transaction.getAccountNumber());
        if (account.isPresent()) {
            //save transaction
            AccountEntity acc = account.get();
            topUpTransaction.setAccount(acc);
            transactionRepository.save(topUpTransaction);
            //change account balance
            Map<String, AccountEntity> mapAcc = new HashMap<>();
            mapAcc.put("transaction", acc);
            doCalculation(mapAcc, transaction, accountRepository);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The user with the number: " + transaction.getAccountNumber() + "account was not found");
        }
    }

    public void doTransfer(TransactionDto transaction, Long toAccountNumber) {
        TransactionEntity transferTransactionFrom = toEntityConverter.convertToTransactionEntity(transaction);
        TransactionEntity transferTransactionTo = toEntityConverter.convertToTransactionEntity(transaction);
        Optional<AccountEntity> accountFrom = accountRepository.findByUserNumber(transaction.getAccountNumber());
        Optional<AccountEntity> accountTo = accountRepository.findByUserNumber(toAccountNumber);

        if (accountFrom.isPresent() && accountTo.isPresent()) {
            //save transaction
            AccountEntity accFrom = accountFrom.get();
            AccountEntity accTo = accountTo.get();

            transferTransactionFrom.setAccount(accFrom);
            transferTransactionTo.setAccount(accTo);

            transactionRepository.save(transferTransactionFrom);
            transactionRepository.save(transferTransactionTo);
            //change account balance
            Map<String, AccountEntity> mapAcc = new HashMap<>();
            mapAcc.put("transferTo", accTo);
            mapAcc.put("transferFrom", accFrom);

            doCalculation(mapAcc, transaction, accountRepository);
        }
    }

    protected abstract void doCalculation(Map<String, AccountEntity> mapAccounts, TransactionDto transaction, SavingsAccountRepository accountRepository);
}

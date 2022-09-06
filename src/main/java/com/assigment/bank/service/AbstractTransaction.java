package com.assigment.bank.service;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractTransaction {

    protected final TransactionRepository transactionRepository;
    protected final SavingsAccountRepository accountRepository;
    private final ModelToEntityConverter toEntityConverter;


    protected AbstractTransaction(TransactionRepository transactionRepository, SavingsAccountRepository accountRepository, ModelToEntityConverter toEntityConverter) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.toEntityConverter = toEntityConverter;
    }

    @Transactional
    public void doOperation(TransactionDto transaction) {
        TransactionEntity topUpTransaction = toEntityConverter.convertToTransactionEntity(transaction);
        Optional<AccountEntity> account = accountRepository.findByUserNumber(transaction.getAccountNumber());
        if (account.isPresent()) {
            //save transaction
            AccountEntity acc = account.get();
            topUpTransaction.setAccount(acc);
            //change account balance
            Map<String, AccountEntity> mapAcc = new HashMap<>();
            mapAcc.put("baseTransactionAcc", acc);
            Map<String, TransactionEntity> mapTransactions = new HashMap<>();
            mapTransactions.put("baseTransaction", topUpTransaction);
            doCalculation(mapAcc, mapTransactions, transaction);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The user with the number: " + transaction.getAccountNumber() + " account was not found");
        }
    }

    public void doTransfer(TransactionDto transaction, Long toAccountNumber) {
        TransactionEntity transferTransactionFrom = toEntityConverter.convertToTransactionEntity(transaction);
        TransactionEntity transferTransactionTo = toEntityConverter.convertToTransactionEntity(transaction);
        Optional<AccountEntity> accountFrom = accountRepository.findByUserNumber(transaction.getAccountNumber());
        Optional<AccountEntity> accountTo = accountRepository.findByUserNumber(toAccountNumber);

        if (accountFrom.isPresent() && accountTo.isPresent()) {
            AccountEntity accFrom = accountFrom.get();
            AccountEntity accTo = accountTo.get();

            //create accounts
            transferTransactionFrom.setAccount(accFrom);
            transferTransactionTo.setAccount(accTo);

            Map<String, AccountEntity> mapAccounts = new HashMap<>();
            mapAccounts.put("transferAccTo", accTo);
            mapAccounts.put("transferAccFrom", accFrom);

            Map<String, TransactionEntity> mapTransactions = new HashMap<>();
            mapTransactions.put("transferTransactionTo", transferTransactionTo);
            mapTransactions.put("transferTransactionFrom", transferTransactionFrom);

            //change account balance
            doCalculation(mapAccounts, mapTransactions, transaction);
        }
    }

    protected abstract void doCalculation(Map<String, AccountEntity> mapAccounts, Map<String, TransactionEntity> mapTransactions, TransactionDto transaction);
}

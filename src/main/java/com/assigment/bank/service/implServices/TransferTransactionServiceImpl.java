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
public class TransferTransactionServiceImpl extends AbstractTransaction {

    @Autowired
    protected TransferTransactionServiceImpl(TransactionRepository transactionRepository
            , SavingsAccountRepository accountRepository
            , ModelToEntityConverter toEntityConverter) {
        super(transactionRepository, accountRepository, toEntityConverter);
    }

    @Override
    protected void doCalculation(Map<String, AccountEntity> mapAccounts, Map<String, TransactionEntity> mapTransactions, TransactionDto transaction) {
        AccountEntity accFrom = mapAccounts.get("transferAccFrom");
        AccountEntity accTo = mapAccounts.get("transferAccTo");
        TransactionEntity transactionTo = mapTransactions.get("transferTransactionTo");
        TransactionEntity transactionFrom = mapTransactions.get("transferTransactionFrom");

        BigDecimal fromAccountBalance = accFrom.getAccountBalance();
        BigDecimal toAccountBalance = accTo.getAccountBalance();
        BigDecimal transactionSum = transaction.getTxAmount();

        if (fromAccountBalance.compareTo(transactionSum) < 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "The account balance is less then minus transaction!");
        } else {
            //change from account balance
            BigDecimal resultedFromAccBalance = fromAccountBalance.subtract(transactionSum);
            accFrom.setAccountBalance(resultedFromAccBalance);
            //save transaction
            accountRepository.saveAndFlush(accFrom);
            transactionRepository.saveAndFlush(transactionFrom);

            //change to account balance
            BigDecimal resultedToAccountBalance = toAccountBalance.add(transactionSum);
            accTo.setAccountBalance(resultedToAccountBalance);
            //save transaction
            accountRepository.saveAndFlush(accTo);
            transactionRepository.saveAndFlush(transactionTo);
        }
    }
}

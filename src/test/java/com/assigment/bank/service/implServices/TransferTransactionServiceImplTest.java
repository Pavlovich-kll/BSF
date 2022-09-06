package com.assigment.bank.service.implServices;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.TransactionRepository;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransferTransactionServiceImplTest extends AbstractServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private SavingsAccountRepository accountRepository;
    @Mock
    private ModelToEntityConverter toEntityConverter;
    @Mock
    Appender<ILoggingEvent> appender;

    @Spy
    @InjectMocks
    private TransferTransactionServiceImpl service;

    private TransactionEntity transactionEntity;
    protected AccountEntity accountEntity2;

    @Before
    public void initTransactions() {
        Logger logger = (Logger) LoggerFactory.getLogger(TransferTransactionServiceImpl.class);
        logger.addAppender(appender);
        transactionEntity = TransactionEntity.builder()
                .txAmount(BigDecimal.valueOf(100))
                .txType("transfer")
                .account(accountEntity)
                .id(1L)
                .build();

        accountEntity2 = AccountEntity.builder()
                .id(2L)
                .accountBalance(BigDecimal.valueOf(9000))
                .accountStatus("enabled")
                .accountType("Dollar")
                .build();

    }

    /**
     * User has enough money on account to transfer to another user
     */
    @Test
    public void doTransfer() {
        TransactionDto transaction = TransactionDto.builder()
                .accountNumber(1L)
                .txAmount(BigDecimal.valueOf(10000))
                .build();

        when(toEntityConverter.convertToTransactionEntity(transaction)).thenReturn(transactionEntity).thenReturn(transactionEntity);
        when(accountRepository.findByUserNumber(1L)).thenReturn(Optional.ofNullable(accountEntity));
        when(accountRepository.findByUserNumber(2L)).thenReturn(Optional.ofNullable(accountEntity2));
        service.doTransfer(transaction, 2L);

        ArgumentCaptor<ILoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(ILoggingEvent.class);
        verify(appender, times(1)).doAppend(eventArgumentCaptor.capture());
        Assertions.assertEquals("Transfer transaction was successful"
                , eventArgumentCaptor.getValue().getMessage());
    }

    /**
     * User has less money on account then he wants to transfer to another user
     */
    @Test(expected = ResponseStatusException.class)
    public void doTransferWithException() {
        TransactionDto transaction = TransactionDto.builder()
                .accountNumber(1L)
                .txAmount(BigDecimal.valueOf(11000))
                .build();

        when(toEntityConverter.convertToTransactionEntity(transaction)).thenReturn(transactionEntity).thenReturn(transactionEntity);
        when(accountRepository.findByUserNumber(1L)).thenReturn(Optional.ofNullable(accountEntity));
        when(accountRepository.findByUserNumber(2L)).thenReturn(Optional.ofNullable(accountEntity2));
        service.doTransfer(transaction, 2L);
    }
}
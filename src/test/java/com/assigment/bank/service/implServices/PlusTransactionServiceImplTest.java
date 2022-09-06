package com.assigment.bank.service.implServices;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.exceptionHandler.exception.AccountNotFoundException;
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

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlusTransactionServiceImplTest extends AbstractServiceTest {

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
    private PlusTransactionServiceImpl service;

    private TransactionEntity transactionEntity;

    @Before
    public void initTransactions() {
        Logger logger = (Logger) LoggerFactory.getLogger(PlusTransactionServiceImpl.class);
        logger.addAppender(appender);
        transactionEntity = TransactionEntity.builder()
                .txAmount(BigDecimal.valueOf(10000))
                .txType("toUp")
                .account(accountEntity)
                .id(1L)
                .build();

    }

    @Test
    public void doOperation() {
        TransactionDto transaction = TransactionDto.builder()
                .accountNumber(1L)
                .txAmount(BigDecimal.valueOf(10000))
                .build();
        when(toEntityConverter.convertToTransactionEntity(transaction)).thenReturn(transactionEntity);
        when(accountRepository.findByUserNumber(any())).thenReturn(Optional.ofNullable(accountEntity));
        service.doOperation(transaction);

        ArgumentCaptor<ILoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(ILoggingEvent.class);
        verify(appender, times(1)).doAppend(eventArgumentCaptor.capture());
        Assertions.assertEquals("You did successfully plus transaction"
                , eventArgumentCaptor.getValue().getMessage());
    }

    @Test(expected = AccountNotFoundException.class)
    public void doOperationWithException() {
        TransactionDto transaction = TransactionDto.builder()
                .accountNumber(1L)
                .txAmount(BigDecimal.valueOf(10000))
                .build();
        when(toEntityConverter.convertToTransactionEntity(transaction)).thenReturn(transactionEntity);
        when(accountRepository.findByUserNumber(any())).thenReturn(Optional.empty());
        service.doOperation(transaction);
    }
}
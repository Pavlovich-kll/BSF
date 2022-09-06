package com.assigment.bank.service.implServices;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.assigment.bank.dto.AccountDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.AddressEntity;
import com.assigment.bank.entity.BankEntity;
import com.assigment.bank.entity.ContactEntity;
import com.assigment.bank.entity.UserEntity;
import com.assigment.bank.exceptionHandler.exception.AccountNotFoundException;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.UserRepository;
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
public class AccountServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private SavingsAccountRepository accountRepository;
    @Mock
    private ModelToEntityConverter toEntityConverter;
    @Mock
    Appender<ILoggingEvent> appender;

    @Spy
    @InjectMocks
    AccountServiceImpl service;

    private UserEntity userEntity;
    private ContactEntity contactEntity;
    private BankEntity bankEntity;
    private AddressEntity addressEntity;
    private AccountEntity accountEntity;
    private AccountDto accountDto;

    @Before
    public void init() {
        Logger logger = (Logger) LoggerFactory.getLogger(AccountServiceImpl.class);
        logger.addAppender(appender);
        accountDto = AccountDto.builder()
                .accountBalance(BigDecimal.valueOf(10000))
                .accountStatus("enabled")
                .accountType("Dollar")
                .build();
        accountEntity = AccountEntity.builder()
                .id(1L)
                .accountBalance(BigDecimal.valueOf(10000))
                .accountStatus("enabled")
                .accountType("Dollar")
                .build();
        contactEntity = ContactEntity.builder()
                .address("Street")
                .city("City")
                .country("Country")
                .email("email@gmail.com")
                .phoneNumber("+79218981929")
                .build();

        addressEntity = AddressEntity.builder()
                .address("Street 10")
                .city("New-York")
                .country("USA")
                .id(1L)
                .build();

        bankEntity = BankEntity.builder()
                .branchAddress(addressEntity)
                .branchCode(123456789)
                .branchName("branchName1")
                .routingNumber(111111)
                .build();

        userEntity = UserEntity.builder()
                .id(1L)
                .firstName("Kirill")
                .lastName("Pavlovich")
                .status("enabled")
                .contactEntity(contactEntity)
                .bank(bankEntity)
                .build();
    }

    @Test
    public void createUsersAccount() {
        when(toEntityConverter.convertToAccountEntity(accountDto)).thenReturn(accountEntity);
        when(userRepository.findById((Long) any())).thenReturn(Optional.ofNullable(userEntity));
        when(accountRepository.saveAndFlush(any())).thenReturn(accountEntity);
        when(userRepository.saveAndFlush(any())).thenReturn(userEntity);
        service.createUsersAccount(accountDto);

        ArgumentCaptor<ILoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(ILoggingEvent.class);
        verify(appender, times(1)).doAppend(eventArgumentCaptor.capture());
        Assertions.assertEquals("account created"
                , eventArgumentCaptor.getValue().getMessage());
    }

    @Test(expected = AccountNotFoundException.class)
    public void createUserAccountWithException() {
        service.createUsersAccount(accountDto);
    }
}
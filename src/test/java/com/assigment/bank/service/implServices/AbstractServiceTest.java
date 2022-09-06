package com.assigment.bank.service.implServices;

import com.assigment.bank.dto.AccountDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.AddressEntity;
import com.assigment.bank.entity.BankEntity;
import com.assigment.bank.entity.ContactEntity;
import com.assigment.bank.entity.UserEntity;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractServiceTest {

    protected UserEntity userEntityNoAccount;
    protected UserEntity userEntityAccount;
    protected ContactEntity contactEntity;
    protected BankEntity bankEntity;
    protected AddressEntity addressEntity;
    protected AccountEntity accountEntity;
    protected AccountDto accountDto;

    @Before
    public void init() {
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
                .transactionsList(new ArrayList<>())
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

        userEntityNoAccount = UserEntity.builder()
                .id(1L)
                .firstName("Kirill")
                .lastName("Pavlovich")
                .status("enabled")
                .contactEntity(contactEntity)
                .bank(bankEntity)
                .build();

        userEntityAccount = UserEntity.builder()
                .id(1L)
                .firstName("Kirill")
                .lastName("Pavlovich")
                .status("enabled")
                .contactEntity(contactEntity)
                .savingsAccount(accountEntity)
                .bank(bankEntity)
                .build();
    }
}

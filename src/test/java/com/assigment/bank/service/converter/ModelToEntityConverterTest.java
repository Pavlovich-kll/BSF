package com.assigment.bank.service.converter;

import com.assigment.bank.dto.AccountDto;
import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.dto.UserDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.entity.UserEntity;
import com.assigment.bank.model.ContactInformation;
import com.assigment.bank.service.implServices.AbstractServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.math.BigDecimal;

public class ModelToEntityConverterTest extends AbstractServiceTest {

    @Spy
    ModelToEntityConverter modelToEntityConverter;

    protected UserDto userDto;
    protected TransactionDto transactionDto;

    @Before
    public void init() {
        transactionDto = TransactionDto.builder()
                .txAmount(BigDecimal.valueOf(100))
                .txType("transfer")
                .accountNumber(1L)
                .build();
        accountDto = AccountDto.builder()
                .accountBalance(BigDecimal.valueOf(10000))
                .accountStatus("enabled")
                .accountType("Dollar")
                .build();

        userDto = UserDto.builder()
                .userNumber(1L)
                .firstName("Kirill")
                .lastName("Pavlovich")
                .status("enabled")
                .contactInformation(new ContactInformation())
                .bankId(1L)
                .build();
    }

    @Test
    public void convertToUserEntity() {
        UserEntity userEntity = modelToEntityConverter.convertToUserEntity(userDto);
        Assert.assertEquals("Kirill", userEntity.getFirstName());
    }

    @Test
    public void convertToAccountEntity() {
        AccountEntity accountEntity = modelToEntityConverter.convertToAccountEntity(accountDto);
        Assert.assertEquals("Dollar", accountEntity.getAccountType());
    }

    @Test
    public void convertToTransactionEntity() {
        TransactionEntity transactionEntity = modelToEntityConverter.convertToTransactionEntity(transactionDto);
        Assert.assertEquals("transfer", transactionEntity.getTxType());
    }
}
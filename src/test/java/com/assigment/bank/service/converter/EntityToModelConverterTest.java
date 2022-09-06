package com.assigment.bank.service.converter;

import com.assigment.bank.entity.TransactionEntity;
import com.assigment.bank.model.Address;
import com.assigment.bank.model.ContactInformation;
import com.assigment.bank.model.Transaction;
import com.assigment.bank.model.User;
import com.assigment.bank.service.implServices.AbstractServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Spy;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class EntityToModelConverterTest extends AbstractServiceTest {

    @Spy
    EntityToModelConverter entityToModelConverter;

    @Test
    public void convertToUserDomain() {
        User user = entityToModelConverter.convertToUserDomain(userEntityAccount);
        Assert.assertEquals("Kirill", user.getFirstName());
    }

    @Test
    public void convertToUserNoAccountDomain() {
        User user = entityToModelConverter.convertToUserNoAccountDomain(userEntityNoAccount);
        Assert.assertEquals("Kirill", user.getFirstName());
    }

    @Test
    public void convertToAddressDomain() {
        Address address = entityToModelConverter.convertToAddressDomain(addressEntity);
        Assert.assertEquals("New-York", address.getCity());
    }

    @Test
    public void convertToContactInformationDomain() {
        ContactInformation contactInformation = entityToModelConverter.convertToContactInformationDomain(contactEntity);
        Assert.assertEquals("email@gmail.com", contactInformation.getEmail());
    }

    @Test
    public void convertToTransactionDomain() {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .txAmount(BigDecimal.valueOf(100))
                .txType("transfer")
                .account(accountEntity)
                .id(1L)
                .build();
        Transaction transaction = entityToModelConverter.convertToTransactionDomain(transactionEntity, 1L);
        Assert.assertEquals("transfer", transaction.getTxType());
    }
}
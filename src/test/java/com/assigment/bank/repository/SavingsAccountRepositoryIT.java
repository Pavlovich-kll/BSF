package com.assigment.bank.repository;

import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.repository.Initializer.ContainerDB;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Optional;

@SqlGroup({
        @Sql("/init-test.sql")
})
public class SavingsAccountRepositoryIT extends ContainerDB {

    @Autowired
    SavingsAccountRepository repository;

    @Test
    public void findByUserNumber() {
        Optional<AccountEntity> account = repository.findByUserNumber(1L);
        Assert.assertNotNull(account);
    }
}
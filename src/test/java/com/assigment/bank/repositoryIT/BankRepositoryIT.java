package com.assigment.bank.repositoryIT;

import com.assigment.bank.entity.BankEntity;
import com.assigment.bank.repository.BankRepository;
import com.assigment.bank.repositoryIT.Initializer.ContainerDB;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Optional;

@SqlGroup({
        @Sql("/init-test.sql")
})
public class BankRepositoryIT extends ContainerDB {

    @Autowired
    BankRepository repository;

    @Test
    public void findById() {
        Optional<BankEntity> bank = repository.findById(1L);
        Assert.assertNotNull(bank.get());
    }
}
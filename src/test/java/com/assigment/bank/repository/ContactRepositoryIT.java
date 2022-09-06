package com.assigment.bank.repository;

import com.assigment.bank.entity.ContactEntity;
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
public class ContactRepositoryIT extends ContainerDB {

    @Autowired
    ContactRepository repository;

    @Test
    public void findById() {
        Optional<ContactEntity> contact = repository.findByEmail("kirill@gmail.com");
        Assert.assertNotNull(contact);
    }
}
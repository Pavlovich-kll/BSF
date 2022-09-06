package com.assigment.bank.repository;

import com.assigment.bank.entity.UserEntity;
import com.assigment.bank.repository.Initializer.ContainerDB;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Optional;

import static org.junit.Assert.*;

@SqlGroup({
        @Sql("/init-test.sql")
})
public class UserRepositoryIT extends ContainerDB {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findById() {
        Optional<UserEntity> user = userRepository.findById(1L);
        Assert.assertNotNull(user);
    }

    @Test
    public void deleteById() {
        userRepository.deleteById(1L);
        boolean userIsPresent = userRepository.findById(1L).isPresent();
        Assert.assertFalse(userIsPresent);
    }
}
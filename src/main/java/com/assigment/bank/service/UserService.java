package com.assigment.bank.service;

import com.assigment.bank.entity.UserEntity;
import com.assigment.bank.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    UserEntity getUsersByNumber(Long userNumber);

    void updateOrCreateUser(User User);

    void deleteUserAndAccount(Long userNumber);
}

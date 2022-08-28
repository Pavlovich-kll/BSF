package com.assigment.bank.service;

import com.assigment.bank.dto.UserDto;
import com.assigment.bank.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUsersByNumber(Long userNumber);

    Long createUser(UserDto User);

    void deleteUserAndAccount(Long userNumber);
}

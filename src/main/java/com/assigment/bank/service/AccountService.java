package com.assigment.bank.service;

import com.assigment.bank.dto.AccountDto;

public interface AccountService {

//    AccountEntity findAccountByAccountNumber(Long accountNumber);

    void createUsersAccount(AccountDto account);
}

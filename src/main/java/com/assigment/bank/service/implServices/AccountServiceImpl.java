package com.assigment.bank.service.implServices;

import com.assigment.bank.dto.AccountDto;
import com.assigment.bank.entity.AccountEntity;
import com.assigment.bank.entity.UserEntity;
import com.assigment.bank.repository.SavingsAccountRepository;
import com.assigment.bank.repository.UserRepository;
import com.assigment.bank.service.AccountService;
import com.assigment.bank.service.converter.EntityToModelConverter;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final SavingsAccountRepository accountRepository;
    private final ModelToEntityConverter toEntityConverter;

    public AccountServiceImpl(UserRepository userRepository, SavingsAccountRepository accountRepository, ModelToEntityConverter toEntityConverter, EntityToModelConverter toModelConverter) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.toEntityConverter = toEntityConverter;
    }

    @Override
    public void createUsersAccount(AccountDto account) {
        AccountEntity newAccount = toEntityConverter.convertToAccountEntity(account);
        Optional<UserEntity> user = userRepository.findById(account.getUserNumber());
        if (user.isPresent()) {
            newAccount.setUserNumber(user.get());
            AccountEntity accountEntity = accountRepository.saveAndFlush(newAccount);
            user.get().setSavingsAccount(accountEntity);
            userRepository.saveAndFlush(user.get());
            log.info("account created");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The user with the number: " + account.getUserNumber() + " was not found!");
        }
    }
}

package com.assigment.bank.repository;

import com.assigment.bank.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends CrudRepository<AccountEntity, String> {

//    Optional<AccountEntity> findByAccountNumber(Long accountNumber);
}

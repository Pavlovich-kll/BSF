package com.assigment.bank.repository;

import com.assigment.bank.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends JpaRepository<AccountEntity, String> {

    @Query(value = "SELECT * FROM account WHERE id = (?1)", nativeQuery = true)
    Optional<AccountEntity> findByUserNumber(Long userNumber);
}

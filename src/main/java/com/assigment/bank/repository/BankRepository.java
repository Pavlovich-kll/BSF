package com.assigment.bank.repository;

import com.assigment.bank.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, String> {

    @Query(value = "SELECT * FROM bank WHERE id = (?1)", nativeQuery = true)
    Optional<BankEntity> findById(Long bankId);
}

package com.assigment.bank.repository;

import com.assigment.bank.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, String> {

//    Optional<List<TransactionEntity>> findByAccountNumber(Long accountNumber);
}

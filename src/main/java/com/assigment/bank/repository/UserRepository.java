package com.assigment.bank.repository;

import com.assigment.bank.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByUserNumber(Long userNumber);
    void deleteByUserNumber(Long userNumber);
}

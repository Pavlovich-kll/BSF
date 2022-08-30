package com.assigment.bank.repository;

import com.assigment.bank.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findById(Long userNumber);
    void deleteById(Long userNumber);
}

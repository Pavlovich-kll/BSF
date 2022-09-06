package com.assigment.bank.repository;

import com.assigment.bank.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, String> {

    Optional<ContactEntity> findByEmail(String email);
}

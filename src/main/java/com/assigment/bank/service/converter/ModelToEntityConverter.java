package com.assigment.bank.service.converter;

import com.assigment.bank.entity.*;
import com.assigment.bank.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelToEntityConverter {

    public UserEntity convertToUserEntity(User user) {
        return UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userNumber(user.getUserNumber())
                .status(user.getStatus())
                .contactDetails(convertToContactEntity(user.getContactInformation()))
                .userAddress(convertToAddressEntity(user.getUserAddress()))
                .savingsAccount(convertToAccountEntity(user.getAccount()))
                .bank(convertToBankInfoEntity(user.getBank()))
                .build();
    }

    public AccountEntity convertToAccountEntity(Account accInfo) {
        List<TransactionEntity> list = new ArrayList<>();
        accInfo.getTransactionList().forEach(t -> list.add(convertToTransactionEntity(t)));

        return AccountEntity.builder()
                .accountType(accInfo.getAccountType())
                .accountBalance(accInfo.getAccountBalance())
                .userEntity(convertToUserEntity(accInfo.getUser()))
                .accountStatus(accInfo.getAccountStatus())
                .transactionsList(list)
                .createDateTime(accInfo.getAccountCreated())
                .build();
    }

    public AddressEntity convertToAddressEntity(Address address) {
        return AddressEntity.builder()
                .address(address.getAddress())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .country(address.getCountry())
                .build();
    }

    public ContactEntity convertToContactEntity(ContactInformation contact) {
        return ContactEntity.builder()
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .build();
    }

    public BankEntity convertToBankInfoEntity(Bank bank) {
        List<UserEntity> listUsers = new ArrayList<>();
        bank.getUser().forEach(u -> listUsers.add(convertToUserEntity(u)));

        return BankEntity.builder()
                .branchCode(bank.getBranchCode())
                .branchName(bank.getBranchName())
                .routingNumber(bank.getRoutingNumber())
                .branchAddress(convertToAddressEntity(bank.getBranchAddress()))
                .userEntity(listUsers)
                .build();
    }

    public TransactionEntity convertToTransactionEntity(Transaction transactionDetails) {
        return TransactionEntity.builder()
                .txAmount(transactionDetails.getTxAmount())
                .txDateTime(transactionDetails.getTxDateTime())
                .txType(transactionDetails.getTxType())
                .account(convertToAccountEntity(transactionDetails.getAccount()))
                .build();
    }
}

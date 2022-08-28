package com.assigment.bank.service.converter;

import com.assigment.bank.dto.AccountDto;
import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.dto.UserDto;
import com.assigment.bank.entity.*;
import com.assigment.bank.model.*;
import org.springframework.stereotype.Component;

@Component
public class ModelToEntityConverter {

    public UserEntity convertToUserEntity(UserDto user) {
        return UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .status(user.getStatus())
                .build();
    }

    public AccountEntity convertToAccountEntity(AccountDto accInfo) {
        return AccountEntity.builder()
                .accountType(accInfo.getAccountType())
                .accountBalance(accInfo.getAccountBalance())
//                .user(convertToUserEntity(accInfo.getUser()))
                .accountStatus(accInfo.getAccountStatus())
//                .transactionsList(list)
//                .createDateTime(accInfo.getAccountCreated())
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
                .address(contact.getAddress())
                .city(contact.getCity())
                .country(contact.getCountry())
                .build();
    }

    public BankEntity convertToBankInfoEntity(Bank bank) {
//        List<UserEntity> listUsers = new ArrayList<>();
//        bank.getUser().forEach(u -> listUsers.add(convertToUserEntity(u)));

        return BankEntity.builder()
                .branchCode(bank.getBranchCode())
                .branchName(bank.getBranchName())
                .routingNumber(bank.getRoutingNumber())
                .branchAddress(convertToAddressEntity(bank.getBranchAddress()))
//                .userEntity(listUsers)
                .build();
    }

    public TransactionEntity convertToTransactionEntity(TransactionDto transaction) {
        return TransactionEntity.builder()
                .txAmount(transaction.getTxAmount())
                .txType(transaction.getTxType())
                .build();
    }




    public BankEntity convertToBankTESTEntity(Bank bank) {
//        List<UserEntity> listUsers = new ArrayList<>();
//        bank.getUser().forEach(u -> listUsers.add(convertToUserEntity(u)));

        return BankEntity.builder()
                .branchCode(bank.getBranchCode())
                .branchName(bank.getBranchName())
                .routingNumber(bank.getRoutingNumber())
                .branchAddress(convertToAddressEntity(bank.getBranchAddress()))
//                .userEntity(listUsers)
                .build();
    }
}

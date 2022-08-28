package com.assigment.bank.service.converter;

import com.assigment.bank.entity.*;
import com.assigment.bank.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityToModelConverter {
    public User convertToUserDomain(UserEntity userEntity) {
        return User.builder()
                .userNumber(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .status(userEntity.getStatus())
                .contactInformation(convertToContactInformationDomain(userEntity.getContactEntity()))
                .account(convertToAccountDomain(userEntity.getSavingsAccount(), userEntity.getId()))
                .bank(convertToBankDomain(userEntity.getBank()))
                .build();
    }

    public User convertToUserNoAccountDomain(UserEntity userEntity) {
        return User.builder()
                .userNumber(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .status(userEntity.getStatus())
                .contactInformation(convertToContactInformationDomain(userEntity.getContactEntity()))
                .bank(convertToBankDomain(userEntity.getBank()))
                .build();
    }

    public Address convertToAddressDomain(AddressEntity addressEntity) {

        return Address.builder()
                .address(addressEntity.getAddress())
                .city(addressEntity.getCity())
                .state(addressEntity.getState())
                .zip(addressEntity.getZip())
                .country(addressEntity.getCountry())
                .build();
    }

    public ContactInformation convertToContactInformationDomain(ContactEntity contact) {
        return ContactInformation.builder()
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .address(contact.getAddress())
                .city(contact.getCity())
                .country(contact.getCountry())
                .build();
    }

    public Account convertToAccountDomain(AccountEntity account, Long userNumber) {
        List<Transaction> transactionList = new ArrayList<>();
        account.getTransactionsList().forEach(t -> transactionList.add(convertToTransactionDomain(t, userNumber)));

        return Account.builder()
                .accountType(account.getAccountType())
                .accountBalance(account.getAccountBalance())
                .userNumber(userNumber)
                .accountStatus(account.getAccountStatus())
                .transactionList(transactionList)
                .build();
    }


    public Bank convertToBankDomain(BankEntity bank) {
        return Bank.builder()
                .branchCode(bank.getBranchCode())
                .branchName(bank.getBranchName())
                .routingNumber(bank.getRoutingNumber())
                .branchAddress(convertToAddressDomain(bank.getBranchAddress()))
                .build();
    }

    public Transaction convertToTransactionDomain(TransactionEntity transaction, Long userNumber) {
        return Transaction.builder()
                .transactionNumber(transaction.getId())
                .txAmount(transaction.getTxAmount())
                .txDateTime(transaction.getTxDateTime())
                .txType(transaction.getTxType())
                .userNumber(userNumber)
                .build();
    }
}

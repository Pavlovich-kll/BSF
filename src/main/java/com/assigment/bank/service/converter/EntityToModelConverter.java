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
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .userNumber(userEntity.getUserNumber())
                .status(userEntity.getStatus())
                .userAddress(convertToAddressDomain(userEntity.getUserAddress()))
                .contactInformation(convertToContactInformationDomain(userEntity.getContactDetails()))
                .account(convertToAccountDomain(userEntity.getSavingsAccount()))
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
                .build();
    }

    public Account convertToAccountDomain(AccountEntity account) {
        List<Transaction> transactionList = new ArrayList<>();
        account.getTransactionsList().forEach(t -> transactionList.add(convertToTransactionDomain(t)));

        return Account.builder()
                .accountType(account.getAccountType())
                .accountBalance(account.getAccountBalance())
                .user(convertToUserDomain(account.getUserEntity()))
                .accountStatus(account.getAccountStatus())
                .transactionList(transactionList)
                .build();
    }


    public Bank convertToBankDomain(BankEntity bank) {
        List<User> userList = new ArrayList<>();
        bank.getUserEntity().forEach(u -> userList.add(convertToUserDomain(u)));

        return Bank.builder()
                .branchCode(bank.getBranchCode())
                .branchName(bank.getBranchName())
                .routingNumber(bank.getRoutingNumber())
                .branchAddress(convertToAddressDomain(bank.getBranchAddress()))
                .user(userList)
                .build();
    }

    public Transaction convertToTransactionDomain(TransactionEntity transaction) {
        return Transaction.builder()
                .txAmount(transaction.getTxAmount())
                .txDateTime(transaction.getTxDateTime())
                .txType(transaction.getTxType())
                .account(convertToAccountDomain(transaction.getAccount()))
                .build();
    }
}

package com.assigment.bank.model;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String firstName;
    private String lastName;
    private Long userNumber;
    private String status;
    private Address userAddress;
    private ContactInformation contactInformation;
    private Bank bank;
    private Account account;
}
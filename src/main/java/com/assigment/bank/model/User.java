package com.assigment.bank.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userNumber;
    private String firstName;
    private String lastName;
    private String status;
    private ContactInformation contactInformation;
    private Bank bank;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Account account;
}
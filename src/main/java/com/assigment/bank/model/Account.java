package com.assigment.bank.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private User user;
    private String accountStatus;
    private String accountType;
    private BigDecimal accountBalance;
    private Date accountCreated;
    private List<Transaction> transactionList;
}

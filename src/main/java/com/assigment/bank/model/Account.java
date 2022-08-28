package com.assigment.bank.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long userNumber;
    private String accountStatus;
    private String accountType;
    private BigDecimal accountBalance;
    private LocalDateTime accountCreated;
    private List<Transaction> transactionList;
}

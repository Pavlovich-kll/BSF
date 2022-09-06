package com.assigment.bank.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<Transaction> transactionList;
}

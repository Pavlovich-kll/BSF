package com.assigment.bank.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Account account;
    private Date txDateTime;
    private String txType;
    private BigDecimal txAmount;
}

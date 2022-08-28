package com.assigment.bank.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Long transactionNumber;
    private Long userNumber;
    private LocalDateTime txDateTime;
    private String txType;
    private BigDecimal txAmount;
}

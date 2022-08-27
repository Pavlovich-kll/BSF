package com.assigment.bank.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "Transaction")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="tx_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Column(name="tx_date")
    @Temporal(TemporalType.TIME)
    private Date txDateTime;

    @Column(name="tx_type")
    private String txType;

    @Column(name="tx_amount")
    private BigDecimal txAmount;
}
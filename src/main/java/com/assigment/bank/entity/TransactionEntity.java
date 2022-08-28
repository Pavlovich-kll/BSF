package com.assigment.bank.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;



@Entity
@Table(name = "TRANSACTION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Column(name="tx_date")
    private LocalDateTime txDateTime;

    @Column(name="tx_type")
    private String txType;

    @Column(name="tx_amount")
    private BigDecimal txAmount;

    @PrePersist
    protected void onCreate() {
        this.txDateTime = LocalDateTime.now();
    }
}
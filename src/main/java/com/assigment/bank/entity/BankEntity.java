package com.assigment.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Bank")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="bank_id")
    private UUID id;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_code")
    private Integer branchCode;

    @JoinColumn(name = "address_id")
    @OneToOne(cascade=CascadeType.ALL)
    private AddressEntity branchAddress;

    @Column(name = "routing_number")
    private Integer routingNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<UserEntity> userEntity;

}

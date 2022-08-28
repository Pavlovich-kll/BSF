package com.assigment.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Id
    @Column(name = "id")
    private Long id;

    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;

    @OneToOne(mappedBy = "branchAddress")
    private BankEntity bank;

}

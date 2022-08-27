package com.assigment.bank.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
}

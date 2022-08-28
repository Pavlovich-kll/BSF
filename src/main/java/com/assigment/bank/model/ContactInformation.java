package com.assigment.bank.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInformation {

    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
}

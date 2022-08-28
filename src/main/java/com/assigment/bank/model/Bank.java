package com.assigment.bank.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    private String branchName;
    private Integer branchCode;
    private Address branchAddress;
    private Integer routingNumber;
}

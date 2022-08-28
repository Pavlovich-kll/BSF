package com.assigment.bank.dto;

import com.assigment.bank.model.ContactInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userNumber;
    private String firstName;
    private String lastName;
    private String status;
    private ContactInformation contactInformation;
    private Long bankId;
    private Integer accountId;
}

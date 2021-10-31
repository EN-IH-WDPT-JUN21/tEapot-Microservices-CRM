package com.Ironhack.AccountService.dto;

import com.Ironhack.AccountService.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long id;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private Long opportunityId;
    private Long contactId;

}

package com.Ironhack.AccountService.dto;

import com.Ironhack.AccountService.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private List<Long> opportunities;
    private List<Long> contacts;

}

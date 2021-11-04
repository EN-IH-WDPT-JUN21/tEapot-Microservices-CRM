package com.ironhack.reportservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String industry;
    private int employeeCount;
    private String city;
    private String country;
    private List<Long> opportunities;
    private List<Long> contacts;
}

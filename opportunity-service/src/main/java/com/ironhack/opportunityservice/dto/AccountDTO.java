package com.ironhack.opportunityservice.dto;

import com.ironhack.opportunityservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    @Enumerated(EnumType.STRING)
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private List<Long> opportunities;;
    private List<Long> contacts;
}

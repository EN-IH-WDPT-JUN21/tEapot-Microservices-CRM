package com.ironhack.opportunityservice.dto;

import com.ironhack.opportunityservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Industry industry;
    @Min(1)
    private int employeeCount;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    private List<Long> opportunities;;
    private List<Long> contacts;

    public AccountDTO(Industry industry, int employeeCount, String city, String  country){
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
    }
}

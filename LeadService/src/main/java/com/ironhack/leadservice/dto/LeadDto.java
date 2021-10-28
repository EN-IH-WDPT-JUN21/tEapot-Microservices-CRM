package com.ironhack.leadservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeadDto {

    private Long id;
    private String name;
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNumber;
    @Email
    private String email;
    private String companyName;
    private SalesRepDto salesRep;


    public LeadDto(String name, String phoneNumber, String email, String companyName, SalesRepDto salesRep) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRep = salesRep;
    }
}

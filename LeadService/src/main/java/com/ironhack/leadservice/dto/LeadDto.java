package com.ironhack.leadservice.dto;

import com.ironhack.leadservice.dao.Lead;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeadDto {

    private Long id;
    private String name;
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNumber;
//    @Email
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

    public LeadDto(Optional<Lead> existingLead) {
        this.name = existingLead.get().getName();
        this.phoneNumber = existingLead.get().getPhoneNumber();
        this.email = existingLead.get().getEmail();
        this.companyName = existingLead.get().getCompanyName();
        this.salesRep = new SalesRepDto(existingLead.get().getSalesRepId());
    }
}

package com.ironhack.opportunityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String CompanyName;

    public ContactDTO(LeadDTO leadDTO) {
        setName(leadDTO.getName());
        setPhoneNumber(leadDTO.getPhoneNumber());
        setEmail(leadDTO.getEmail());
        setCompanyName(leadDTO.getCompanyName());
    }
}

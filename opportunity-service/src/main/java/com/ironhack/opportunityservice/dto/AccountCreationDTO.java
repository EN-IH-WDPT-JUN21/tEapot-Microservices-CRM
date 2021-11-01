package com.ironhack.opportunityservice.dto;

import com.ironhack.opportunityservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationDTO {
    @Enumerated(EnumType.STRING)
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private Long opportunityId;
    private Long contactId;

    public AccountCreationDTO(AccountDTO accountDTO, Long opportunityId, Long contactId) {
        setIndustry(accountDTO.getIndustry());
        setEmployeeCount(accountDTO.getEmployeeCount());
        setCity(accountDTO.getCity());
        setCountry(accountDTO.getCountry());
        setOpportunityId(opportunityId);
        setContactId(contactId);
    }
}

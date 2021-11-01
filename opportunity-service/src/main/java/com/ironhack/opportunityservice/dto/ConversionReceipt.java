package com.ironhack.opportunityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversionReceipt {
    private Long deletedLeadId;
    private ContactDTO contactDTO;
    private OpportunityDTO opportunityDTO;
    private AccountDTO accountDTO;
}

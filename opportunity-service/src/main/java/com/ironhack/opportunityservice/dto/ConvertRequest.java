package com.ironhack.opportunityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertRequest {
    private AccountDTO account;
    private OpportunityDTO opportunity;
    private Long leadId;
    private Long salesRepId;
}

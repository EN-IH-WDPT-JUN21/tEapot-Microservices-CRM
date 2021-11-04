package com.ironhack.opportunityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertRequest {
    @Valid
    private AccountDTO account;
    @Valid
    private OpportunityDTO opportunity;
    @NotNull
    private Long leadId;
}

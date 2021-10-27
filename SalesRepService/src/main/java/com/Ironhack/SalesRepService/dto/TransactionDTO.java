package com.Ironhack.SalesRepService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private String name;
    private Long leadId;
    private Long opportunityId;
}

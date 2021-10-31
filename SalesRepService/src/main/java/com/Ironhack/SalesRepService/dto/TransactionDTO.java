package com.Ironhack.SalesRepService.dto;

import com.Ironhack.SalesRepService.enums.Type;
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
    private Type transactionType;

    public TransactionDTO(String name, Long leadId, Long opportunityId) {
        this.name = name;
        this.leadId = leadId;
        this.opportunityId = opportunityId;
    }
}

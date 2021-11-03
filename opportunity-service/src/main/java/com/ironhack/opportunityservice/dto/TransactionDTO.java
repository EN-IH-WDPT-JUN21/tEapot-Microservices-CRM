package com.ironhack.opportunityservice.dto;

import com.ironhack.opportunityservice.enums.Type;
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

    public TransactionDTO(Long leadId, Long opportunityId) {
        this.leadId = leadId;
        this.opportunityId = opportunityId;
    }
}

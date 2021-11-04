package com.ironhack.reportservice.DTO;

import com.ironhack.reportservice.enums.Industry;
import com.ironhack.reportservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
//    private Long id;
    private String name;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private Long leadId;
    private Long opportunityId;
    private Long contactId;
    private Type transactionType;

    public TransactionDTO(String name, Long leadId, Long opportunityId) {
        this.name = name;
        this.leadId = leadId;
        this.opportunityId = opportunityId;
    }

    public TransactionDTO(String name, Industry industry, int employeeCount,
                          String city, String country, Long opportunityId, Long contactId) {
        this.name = name;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.opportunityId = opportunityId;
        this.contactId = contactId;
    }

    public TransactionDTO(String name, Long leadId, Long opportunityId, Type transactionType) {
        this.name = name;
        this.leadId = leadId;
        this.opportunityId = opportunityId;
        this.transactionType = transactionType;
    }
}

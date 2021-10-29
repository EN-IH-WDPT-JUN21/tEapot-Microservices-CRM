package com.ironhack.opportunityservice.dto;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityDTO {

    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Product product;
    private int quantity;
    private Long decisionMakerId;

    public OpportunityDTO(Opportunity opportunity) {
        setId(opportunity.getId());
        setProduct(opportunity.getProduct());
        setQuantity(opportunity.getQuantity());
        setStatus(opportunity.getStatus());
        setDecisionMakerId(opportunity.getDecisionMakerId());
        setSalesRepId(opportunity.getSalesRepId());
    }
}

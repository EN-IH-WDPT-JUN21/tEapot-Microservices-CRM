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
    private Long salesRepId; // TODO - remove

    public OpportunityDTO(Status status, Product product, int quantity, Long decisionMakerId, Long salesRepId){
        setStatus(status);
        setProduct(product);
        setQuantity(quantity);
        setDecisionMakerId(decisionMakerId);
        setSalesRepId(salesRepId);
    }
}

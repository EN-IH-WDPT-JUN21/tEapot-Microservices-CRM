package com.ironhack.opportunityservice.dto;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityDTO {

    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Product product;
    @Min(1)
    private int quantity;
    private Long decisionMakerId;
    @NotNull
    private Long salesRepId;

    public OpportunityDTO(Status status, Product product, int quantity, Long decisionMakerId, Long salesRepId){
        setStatus(status);
        setProduct(product);
        setQuantity(quantity);
        setDecisionMakerId(decisionMakerId);
        setSalesRepId(salesRepId);
    }
}

package com.ironhack.reportservice.DTO;
import com.ironhack.reportservice.enums.Product;
import com.ironhack.reportservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityDTO {
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Product product;
    private int quantity;
    private Long decisionMakerId;
    private Long salesRepId;
}

package com.ironhack.opportunityservice.dao;

import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Product product;
    private int quantity;
    private Long decisionMakerId;
    private Long salesRepId;


    public Opportunity(OpportunityDTO opportunityDTO, Long decisionMakerId) {
        status = Status.OPEN;
        setProduct(opportunityDTO.getProduct());
        setQuantity(opportunityDTO.getQuantity());
        setDecisionMakerId(decisionMakerId);
    }

    // TODO - delete if not needed
/*    public String showOpportunityDetails() {
        String str = "Opportunity details: ".concat("\n")
                .concat("   ID: ").concat(String.valueOf(this.getId())).concat("\n")
                .concat("   Status: ").concat(this.getStatus().name()).concat("\n")
                .concat("   Product: ").concat(this.getProduct().name()).concat("\n")
                .concat("   Quantity: ").concat(String.valueOf(this.getQuantity())).concat("\n")
                .concat("   Contact details: ").concat("\n");
        if (null != this.salesRep) {
            str = str
                    .concat("   SalesRep Id: ").concat(String.valueOf(this.salesRep.getId())).concat("\n")
                    .concat("   SalesRep Name: ").concat(this.salesRep.getName()).concat("\n");
        }

        str = str.concat(this.getDecisionMaker().showContactDetails()).concat("\n");
        return str;
    }*/
}
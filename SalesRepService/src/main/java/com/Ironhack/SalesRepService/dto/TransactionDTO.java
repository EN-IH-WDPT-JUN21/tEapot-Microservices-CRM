package com.Ironhack.SalesRepService.dto;

import com.Ironhack.SalesRepService.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    @Pattern(regexp = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)", groups = Create.class)
    private String name;
    private Long leadId;
    private Long opportunityId;
    @NotNull(groups = Update.class)
    private Type transactionType;

    public TransactionDTO(String name, Long leadId, Long opportunityId) {
        this.name = name;
        this.leadId = leadId;
        this.opportunityId = opportunityId;
    }
}

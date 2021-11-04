package com.ironhack.leadservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SalesRepDto {

    private Long id;
    private String name;
    private List<Long> leads;
    private List<Long> opportunities;
    private String transactionType;


    public SalesRepDto(Long salesRepId) {
        this.id = salesRepId;
    }

    public SalesRepDto(String salesRepName) {
        this.name = salesRepName;
    }

    public SalesRepDto(String salesRepName, String transactionType) {
        this.name = salesRepName;
    }
}

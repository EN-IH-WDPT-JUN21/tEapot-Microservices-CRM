package com.ironhack.leadservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SalesRepDto {

    private Long id;
    private String name;


    public SalesRepDto(Long salesRepId) {
        this.id = salesRepId;
    }

    public SalesRepDto(String salesRepName) {
        this.name = salesRepName;
    }
}

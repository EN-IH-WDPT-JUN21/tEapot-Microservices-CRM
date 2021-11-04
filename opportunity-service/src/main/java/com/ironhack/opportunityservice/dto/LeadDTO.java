package com.ironhack.opportunityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String CompanyName;
}

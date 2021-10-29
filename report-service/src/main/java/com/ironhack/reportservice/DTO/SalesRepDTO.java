package com.ironhack.reportservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesRepDTO {
    private Long id;
    private String repName;
    private List<Long> leadList;
    private List<Long> opportunityList;
}

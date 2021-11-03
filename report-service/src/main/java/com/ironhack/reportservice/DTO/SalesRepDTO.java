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
    private String name;
    private List<Long> leads;
    private List<Long> opportunities;
}

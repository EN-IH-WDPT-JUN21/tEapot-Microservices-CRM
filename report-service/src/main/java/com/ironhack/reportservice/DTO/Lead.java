package com.ironhack.reportservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lead {
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    private Long salesrepId;

    //possibly, won't need this one
}

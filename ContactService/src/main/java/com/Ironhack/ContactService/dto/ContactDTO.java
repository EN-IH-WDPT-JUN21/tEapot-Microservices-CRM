package com.Ironhack.ContactService.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

}

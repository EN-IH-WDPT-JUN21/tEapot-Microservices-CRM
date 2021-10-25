package com.Ironhack.ContactService.dao;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "contact_id")
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

}

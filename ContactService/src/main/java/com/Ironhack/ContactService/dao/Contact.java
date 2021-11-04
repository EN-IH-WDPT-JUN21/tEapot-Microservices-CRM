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

    public Contact(String name, String phoneNumber, String email, String companyName) {
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.companyName=companyName;
    }
}

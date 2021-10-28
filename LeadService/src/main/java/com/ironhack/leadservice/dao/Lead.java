package com.ironhack.leadservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="LEAD_DETAILS")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNumber;

    private String companyName;

    @Email
    private String email;
    private Long salesRepId;

    public Lead(String name, String phoneNumber, String companyName, String email, Long salesRepId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.email = email;
        this.salesRepId = salesRepId;
    }
}

package com.Ironhack.AccountService.dao;

import com.Ironhack.AccountService.enums.Industry;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import reactor.util.annotation.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "account_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Industry industry;
    @Column(name = "employee_count")
    @NotNull
    private int employeeCount;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @NotNull
    private List<Long> opportunities;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @NotNull
    private List<Long> contacts;

    public Account(String city, String country, int employeeCount, Industry industry, List opportunities, List contacts) {
        this.city=city;
        this.country=country;
        this.employeeCount=employeeCount;
        this.industry=industry;
        this.opportunities=opportunities;
        this.contacts=contacts;
    }
}
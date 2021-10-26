package com.Ironhack.SalesRepService.dao;

import com.sun.istack.NotNull;
import lombok.*;


import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "sales_rep_id")
    private Long id;

    @NotNull
    private String name;
}

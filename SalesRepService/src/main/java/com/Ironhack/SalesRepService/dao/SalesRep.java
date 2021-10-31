package com.Ironhack.SalesRepService.dao;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.*;
import java.util.List;


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

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @NotNull
    private List<Long> leads;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @NotNull
    private List<Long> opportunities;


}

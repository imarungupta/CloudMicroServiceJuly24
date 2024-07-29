package com.sis.customer.service.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_tbl")
public class Customer {

    @Id
    @Column(name = "ID")
    private String customerId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ABOUT")
    private String about;

    //@Transient
    @Column(name = "URI")
    private URI uri;

    /*@Transient
    private List<Rating> ratingList;*/
    // The above code will give null if there is no value in ratingList
    @Transient
    private List<Rating> ratingList = new ArrayList<>();
    // The above code will give [] in case of empty list instead of null
}

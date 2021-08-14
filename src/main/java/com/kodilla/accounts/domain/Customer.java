package com.kodilla.accounts.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            targetEntity = Account.class,
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Account> accounts = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long id, List<Account> accounts) {
        this.id = id;
        this.accounts = accounts;
    }
}

package com.kodilla.accounts.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nrb")
    private String nrb;

    @Column(name = "currency")
    private String currency;

    @Column(name = "available_funds")
    private BigDecimal availableFunds;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Account() {
    }

    public Account(String nrb, String currency, BigDecimal availableFunds) {
        this.nrb = nrb;
        this.currency = currency;
        this.availableFunds = availableFunds;
    }

    public Account(Long id, String nrb, String currency, BigDecimal availableFunds, Customer customer) {
        this.id = id;
        this.nrb = nrb;
        this.currency = currency;
        this.availableFunds = availableFunds;
        this.customer = customer;
    }
}

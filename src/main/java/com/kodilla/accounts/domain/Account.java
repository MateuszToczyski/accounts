package com.kodilla.accounts.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nrb", unique = true)
    private String nrb;

    @Column(name = "currency")
    private String currency;

    @Column(name = "available_funds")
    private BigDecimal availableFunds;

    @Column(name = "customer_id")
    private Long customerId;

}

package com.kodilla.accounts.repository;

import com.kodilla.accounts.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}

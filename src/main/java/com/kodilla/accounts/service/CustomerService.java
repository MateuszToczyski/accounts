package com.kodilla.accounts.service;

import com.kodilla.accounts.domain.Customer;
import com.kodilla.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}

package com.kodilla.accounts.repository;

import com.kodilla.accounts.domain.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Override
    List<Account> findAll();

    List<Account> findByCustomerId(long customerId);

    Optional<Account> findByNrb(String nrb);

}

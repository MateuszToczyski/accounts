package com.kodilla.accounts.mapper;

import com.kodilla.accounts.domain.Account;
import com.kodilla.accounts.domain.Customer;
import com.kodilla.accounts.dto.AccountDto;
import com.kodilla.accounts.dto.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerMapper {

    private final AccountMapper accountMapper;

    public Customer toCustomer(CustomerDto customerDto) {
        List<Account> accounts = accountMapper.toAccountList(customerDto.getAccounts());
        return new Customer(customerDto.getId(), accounts);
    }

    public CustomerDto toCustomerDto(Customer customer) {
        List<AccountDto> accountDtos = accountMapper.toAccountDtoList(customer.getAccounts());
        return new CustomerDto(customer.getId(), accountDtos);
    }
}

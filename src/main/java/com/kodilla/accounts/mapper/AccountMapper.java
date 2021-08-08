package com.kodilla.accounts.mapper;

import com.kodilla.accounts.domain.Account;
import com.kodilla.accounts.dto.AccountDto;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toAccount(AccountDto accountDto) {
        return new Account(accountDto.getNrb(), accountDto.getCurrency(), accountDto.getAvailableFunds());
    }

    public AccountDto toAccountDto(Account account) {
        return new AccountDto(account.getId(), account.getNrb(), account.getCurrency(), account.getAvailableFunds());
    }
}

package com.kodilla.accounts.controller;

import com.kodilla.accounts.controller.response.CreateAccountResponse;
import com.kodilla.accounts.controller.response.GetAccountsResponse;
import com.kodilla.accounts.dto.AccountDto;
import com.kodilla.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("v1/accounts")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public GetAccountsResponse findAccountById(@RequestParam Long customerId) {
        AccountDto accountDto = accountService.findById(customerId);
        List<AccountDto> resultList = accountDto == null
                ? Collections.emptyList()
                : Collections.singletonList(accountDto);
        return GetAccountsResponse.of(resultList);
    }

    @PostMapping
    public CreateAccountResponse createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccountDto = accountService.createAccount(accountDto);
        return CreateAccountResponse.of(createdAccountDto);
    }
}

package com.kodilla.accounts.controller;

import com.kodilla.accounts.controller.response.CreateAccountResponse;
import com.kodilla.accounts.controller.response.GetAccountResponse;
import com.kodilla.accounts.controller.response.GetAccountsResponse;
import com.kodilla.accounts.dto.AccountDto;
import com.kodilla.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Value("${application.allow-get-accounts}")
    private boolean allowGetAccounts;

    @GetMapping("{id}")
    public GetAccountResponse findAccountById(@PathVariable Long id) {
        AccountDto account = accountService.findById(id);
        if (account != null) {
            return GetAccountResponse.of(account);
        } else {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @GetMapping(params = "nrb")
    public GetAccountResponse findAccountByNrb(@RequestParam("nrb") String nrb) {
        AccountDto account = accountService.findByNrb(nrb);
        if (account != null) {
            return GetAccountResponse.of(account);
        } else {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @GetMapping(params = "customerId")
    public GetAccountsResponse findByCustomerId(@RequestParam("customerId") Long customerId) {
        log.info("Get accounts for customerId: {}", customerId);
        if (!allowGetAccounts) {
            log.info("Getting accounts is disabled");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting accounts is disabled");
        }
        List<AccountDto> accounts = accountService.findByCustomerId(customerId);
        return GetAccountsResponse.of(accounts);
    }

    @PostMapping
    public CreateAccountResponse createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccountDto = accountService.createAccount(accountDto);
        return CreateAccountResponse.of(createdAccountDto);
    }
}
package com.kodilla.accounts.service;

import com.kodilla.accounts.domain.Account;
import com.kodilla.accounts.dto.AccountDto;
import com.kodilla.accounts.enums.TransactionSide;
import com.kodilla.accounts.exception.SenderAccountNotFoundException;
import com.kodilla.accounts.mapper.AccountMapper;
import com.kodilla.accounts.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.kodilla.accounts.enums.TransactionSide.CREDIT;
import static com.kodilla.accounts.enums.TransactionSide.DEBIT;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDto findById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountOptional.map(accountMapper::toAccountDto).orElse(null);
    }

    public AccountDto findByNrb(String nrb) {
        Optional<Account> accountOptional = accountRepository.findByNrb(nrb);
        return accountOptional.map(accountMapper::toAccountDto).orElse(null);
    }

    public List<AccountDto> findByCustomerId(Long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        return accountMapper.toAccountDtoList(accounts);
    }

    public AccountDto createAccount(AccountDto accountDto) {
        Account account = accountRepository.save(accountMapper.toAccount(accountDto));
        return accountMapper.toAccountDto(account);
    }

    public void updateBalances(BigDecimal amount, String senderAccountNrb, String recipientAccountNrb) {
        Account senderAccount = accountRepository.findByNrb(senderAccountNrb)
                .orElseThrow(SenderAccountNotFoundException::new);
        updateBalance(amount, senderAccount, DEBIT);

        Optional<Account> recipientAccount = accountRepository.findByNrb(recipientAccountNrb);
        recipientAccount.ifPresent(account -> updateBalance(amount, account, CREDIT));
    }

    private void updateBalance(BigDecimal amount, Account account, TransactionSide side) {
        BigDecimal currentBalance = account.getAvailableFunds();
        BigDecimal updatedBalance = side == CREDIT ? currentBalance.add(amount) : currentBalance.subtract(amount);
        account.setAvailableFunds(updatedBalance);
        accountRepository.save(account);
    }
}

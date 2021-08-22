package com.kodilla.accounts.controller.response;

import com.kodilla.accounts.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class GetAccountByIdResponse {
    private AccountDto account;
}

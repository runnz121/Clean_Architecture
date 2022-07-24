package com.clean.architecture.account.adapter.in.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clean.architecture.account.application.port.in.SendMoneyCommand;
import com.clean.architecture.account.application.port.in.SendMoneyUseCase;
import com.clean.architecture.account.domain.Account;
import com.clean.architecture.account.domain.Money;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ServiceMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping(path = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    void sendMoney(
        @PathVariable("sourceAccountId") Long sourceAccountId,
        @PathVariable("targetAccountId") Long targetAccountId,
        @PathVariable("amount") Long amount) {

        SendMoneyCommand command = new SendMoneyCommand(
            new Account.AccountId(sourceAccountId),
            new Account.AccountId(targetAccountId),
            Money.of(amount));

        sendMoneyUseCase.sendMoney(command);



    }
}

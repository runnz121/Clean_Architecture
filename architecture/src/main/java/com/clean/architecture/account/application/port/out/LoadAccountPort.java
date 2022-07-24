package com.clean.architecture.account.application.port.out;

import java.time.LocalDateTime;

import com.clean.architecture.account.domain.Account;

public interface LoadAccountPort {

    Account loadAccount(Account.AccountId accountId, LocalDateTime localDateTime);
}

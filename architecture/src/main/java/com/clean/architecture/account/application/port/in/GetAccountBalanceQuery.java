package com.clean.architecture.account.application.port.in;

import com.clean.architecture.account.domain.Account;
import com.clean.architecture.account.domain.Money;

/**
 * 읽기 전용 유스케이스
 */
public interface GetAccountBalanceQuery {

    Money getAccountBalance(Account.AccountId accountId);
}

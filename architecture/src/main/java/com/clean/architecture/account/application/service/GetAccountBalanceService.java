package com.clean.architecture.account.application.service;

import java.time.LocalDateTime;

import com.clean.architecture.account.application.port.in.GetAccountBalanceQuery;
import com.clean.architecture.account.application.port.out.LoadAccountPort;
import com.clean.architecture.account.domain.Account;
import com.clean.architecture.account.domain.Money;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final LoadAccountPort loadAccountPort;

    /**
     * 읽기 전용 in port를 만들고 그것을 구현함
     * @param accountId
     * @return
     */
    @Override
    public Money getAccountBalance(Account.AccountId accountId) {
        /*
        // 인터페이스 반환값이 Account 이다
        Account money = loadAccountPort.loadAccount(accountId, LocalDateTime.now());

        // Account 내부의 메서드를 호출해서 Money 로 반환
        money.calculateBalance();
         */

        return loadAccountPort
            .loadAccount(accountId, LocalDateTime.now())
            .calculateBalance();
    }
}

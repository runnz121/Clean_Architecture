package com.clean.architecture.account.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    void calculatesBalance() {
        Account.AccountId accountId = new Account.AccountId(1L);
        Account account = defaultAccount()
            .withAccountId(accountId)
            .withBaselineBalance(Money.of(555L))
            .withActivityWindow(new ActivityWindow(
                defaultActivity()
                    .withTargetAccount(accountId)
                    .withMoney(Money.of(999L)).build(),
                defaultActivity()
                    .withTargetAccount(accountId)
                    .withMoney(Money.of(1L)).build()))
            .build();

        Money balance = account.calculateBalance();

        assertThat(balance).isEqualTo(Money.of(1555L));
    }

    @Test
    void withdrawalSucceeds() {
        AccountId accountId = new AccountId(1L);
        Account account = defaultAccount()
            .withAccountId(accountId)
            .withBaselineBalance(Money.of(555L))
            .withActivityWindow(new ActivityWindow(
                defaultActivity()
                    .withTargetAccount(accountId)
                    .withMoney(Money.of(999L)).build(),
                defaultActivity()
                    .withTargetAccount(accountId)
                    .withMoney(Money.of(1L)).build()))
            .build();

        boolean success = account.withdraw(Money.of(555L), new AccountId(99L));

        assertThat(success).isTrue();
        assertThat(account.getActivityWindow().getActivities()).hasSize(3);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(1000L));
    }

    @Test
    void withdrawalFailure() {
        AccountId accountId = new AccountId(1L);
        Account account = defaultAccount()
            .withAccountId(accountId)
            .withBaselineBalance(Money.of(555L))
            .withActivityWindow(new ActivityWindow(
                defaultActivity()
                    .withTargetAccount(accountId)
                    .withMoney(Money.of(999L)).build(),
                defaultActivity()
                    .withTargetAccount(accountId)
                    .withMoney(Money.of(1L)).build()))
            .build();

        boolean success = account.withdraw(Money.of(1556L), new AccountId(99L));

        assertThat(success).isFalse();
        assertThat(account.getActivityWindow().getActivities()).hasSize(2);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(1555L));
    }

    @Test
    void depositSuccess() {
        AccountId accountId = new AccountId(1L);
        Account account = defaultAccount()
            .withAccountId(accountId)
            .withBaselineBalance(Money.of(555L))
            .withActivityWindow(new ActivityWindow(
                defaultActivity()
                    .withTargetAccount(accountId)
                    .withMoney(Money.of(999L)).build(),
                defaultActivity()
                    .withTargetAccount(accountId)
                    .withMoney(Money.of(1L)).build()))
            .build();

        boolean success = account.deposit(Money.of(445L), new AccountId(99L));

        assertThat(success).isTrue();
        assertThat(account.getActivityWindow().getActivities()).hasSize(3);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(2000L));
    }


}
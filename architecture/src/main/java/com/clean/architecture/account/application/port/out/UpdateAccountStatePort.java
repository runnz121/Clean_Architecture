package com.clean.architecture.account.application.port.out;

import com.clean.architecture.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);
}

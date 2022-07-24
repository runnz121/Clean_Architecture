package com.clean.architecture.account.application.port.in;

public interface SendMoneyUseCase {

    boolean sendMoney(SendMoneyCommand commend);
}

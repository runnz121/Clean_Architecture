package com.clean.architecture.account.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clean.architecture.account.application.port.in.SendMoneyCommand;
import com.clean.architecture.account.application.port.in.SendMoneyUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

    @Override
    public boolean sendMoney(SendMoneyCommand commend) {
        return false;
    }
}

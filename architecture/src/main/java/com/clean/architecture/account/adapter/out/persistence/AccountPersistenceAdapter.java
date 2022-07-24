package com.clean.architecture.account.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;

import com.clean.architecture.account.application.port.out.LoadAccountPort;
import com.clean.architecture.account.application.port.out.UpdateAccountStatePort;
import com.clean.architecture.account.domain.Account;
import com.clean.architecture.account.domain.Activity;

import lombok.RequiredArgsConstructor;

/**
 * 영속성 기능을 제공하는 영속성 어뎁터
 * 엔티티와 리포지토리가 존재함으로 이를 영속시키는 어댑터를 구현
 */
@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {

    private final SpringDataAccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(
        Account.AccountId accountId,
        LocalDateTime baselineDate) {

        AccountJpaEntity account =
            accountRepository.findById(accountId.getValue())
                .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities =
            activityRepository.findByOwnerSince(
                accountId.getValue(),
                baselineDate);

        Long withdrawalBalance = orZero(activityRepository
            .getWithdrawalBalanceUntil(
                accountId.getValue(),
                baselineDate));

        Long depositBalance = orZero(activityRepository
            .getDepositBalanceUntil(
                accountId.getValue(),
                baselineDate));

        return accountMapper.mapToDomainEntity(
            account,
            activities,
            withdrawalBalance,
            depositBalance);

    }

    private Long orZero(Long value){
        return value == null ? 0L : value;
    }


    @Override
    public void updateActivities(Account account) {
        for (Activity activity : account.getActivityWindow().getActivities()) {
            if (activity.getId() == null) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity));
            }
        }
    }


}

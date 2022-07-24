package com.clean.architecture.account.application.port.in;

import javax.validation.constraints.NotNull;

import com.clean.architecture.account.domain.Account;
import com.clean.architecture.account.domain.Money;
import com.clean.architecture.common.SelfValidating;

import lombok.Value;

/**
 * 이 입력 모델에서 (포트에서) 유효성을 검증한다
 * -> 생성자 내에서 유효성 검증
 */
@Value
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {
    /**
     * bean validation 이 유효성 검증을 처리해줌
     */
    @NotNull
    private final Account.AccountId sourceAccountId;

    @NotNull
    private final Account.AccountId targetAccountId;

    @NotNull
    private final Money money;

    /**
     * 해당 생성자를 생성하면서 유효성에 위배되는 경우 에러가 생긴다(생성자가 생성되지 않음)
     * 빌더 패턴으로 구현하는 것보다 생성자를 이용하여 구현하는 것이 더 좋은 이유 -> ide가 에러를 자동으로 잡아주어 추가적인 생성자 조건을 잊지 않도록 알려주기 때문
     * @param sourceAccountId
     * @param targetAccountId
     * @param money
     */
    public SendMoneyCommand(
        Account.AccountId sourceAccountId,
        Account.AccountId targetAccountId,
        Money money) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
        this.validateSelf(); // 이 validator가 @NotNull로 지정한 bean validation을 재 확인하여 위반 값을 확인한다.
    }
}

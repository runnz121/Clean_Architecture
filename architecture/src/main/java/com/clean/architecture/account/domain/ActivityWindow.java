package com.clean.architecture.account.domain;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import com.clean.architecture.account.domain.Account.AccountId;

public class ActivityWindow {

    private List<Activity> activities;

    public ActivityWindow(List<Activity> mappedActivities) {
    }

    public Money calculateBalance(AccountId id) {

        BigInteger abig = new BigInteger("1000");
        BigInteger bbig = new BigInteger("1000");

        Money a = new Money(abig);
        Money b = new Money(bbig);


        return Money.add(a, b);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }

}

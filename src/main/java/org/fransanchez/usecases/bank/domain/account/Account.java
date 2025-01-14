package org.fransanchez.usecases.bank.domain.account;

import javax.money.MonetaryAmount;

public class Account {
    private final String accountId;
    private final String customerId;
    private MonetaryAmount balance;

    public Account(final String accountId, final String customerId, final MonetaryAmount balance) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.balance = balance;
    }

    public void deposit(final MonetaryAmount amount) {
        balance = balance.add(amount);
    }

    public void withdraw(final MonetaryAmount amount) {
        balance = balance.subtract(amount);
    }

    public String accountId() {
        return accountId;
    }
}

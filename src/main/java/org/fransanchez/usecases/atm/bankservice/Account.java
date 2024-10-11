package org.fransanchez.usecases.atm.bankservice;

import org.fransanchez.usecases.atm.MoneyAmount;

public class Account {
    private final String accountId;
    private MoneyAmount balance;

    public Account(final String accountId, final MoneyAmount balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public MoneyAmount balance() {
        return balance;
    }

    public String accountId() {
        return accountId;
    }

    public MoneyAmount withdraw(final MoneyAmount amount) {
        if (amount.currency() != balance.currency()) {
            throw new IllegalArgumentException("Currency amount does not match accounts");
        }

        final var newBalance = balance.amount().subtract(amount.amount());
        balance = new MoneyAmount(balance.currency(), newBalance);

        return balance;
    }

    public MoneyAmount deposit(final MoneyAmount amount) {
        if (amount.currency() != balance.currency()) {
            throw new IllegalArgumentException("Currency amount does not match accounts");
        }

        final var newBalance = balance.amount().add(amount.amount());
        balance = new MoneyAmount(balance.currency(), newBalance);

        return balance;

    }
}

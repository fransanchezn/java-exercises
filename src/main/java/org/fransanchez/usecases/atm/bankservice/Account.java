package org.fransanchez.usecases.atm.bankservice;

import org.fransanchez.usecases.atm.CustomMoneyAmount;

public class Account {
    private final String accountId;
    private CustomMoneyAmount balance;

    public Account(final String accountId, final CustomMoneyAmount balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public CustomMoneyAmount balance() {
        return balance;
    }

    public String accountId() {
        return accountId;
    }

    public CustomMoneyAmount withdraw(final CustomMoneyAmount amount) {
        if (amount.currency() != balance.currency()) {
            throw new IllegalArgumentException("Currency amount does not match accounts");
        }

        final var newBalance = balance.amount().subtract(amount.amount());
        balance = new CustomMoneyAmount(balance.currency(), newBalance);

        return balance;
    }

    public CustomMoneyAmount deposit(final CustomMoneyAmount amount) {
        if (amount.currency() != balance.currency()) {
            throw new IllegalArgumentException("Currency amount does not match accounts");
        }

        final var newBalance = balance.amount().add(amount.amount());
        balance = new CustomMoneyAmount(balance.currency(), newBalance);

        return balance;

    }
}

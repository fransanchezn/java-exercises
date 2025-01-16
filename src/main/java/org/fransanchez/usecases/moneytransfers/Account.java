package org.fransanchez.usecases.moneytransfers;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.util.UUID;

public class Account {
    private final UUID accountId;
    private MonetaryAmount balance;

    public Account(final CurrencyUnit currency) {
        this.accountId = UUID.randomUUID();
        this.balance = Money.of(0, currency);
    }

    public synchronized MonetaryAmount deposit(final MonetaryAmount amount) {
        balance = balance.add(amount);
        return balance;
    }

    public synchronized MonetaryAmount withdraw(final MonetaryAmount amount) {
        balance = balance.subtract(amount);
        return balance;
    }

    public UUID accountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }
}

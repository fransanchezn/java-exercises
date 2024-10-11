package org.fransanchez.usecases.atm;

public interface AtmService {
    boolean validateUser(final String cardId, final String pinNumber);
    MoneyAmount balance(final String accountId);
    MoneyAmount withdraw(final String accountId, final MoneyAmount moneyAmount);
    MoneyAmount deposit(final String accountId, final MoneyAmount moneyAmount);
}
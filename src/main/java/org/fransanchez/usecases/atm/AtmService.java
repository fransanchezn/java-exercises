package org.fransanchez.usecases.atm;

public interface AtmService {
    boolean validateUser(final String cardId, final String pinNumber);
    CustomMoneyAmount balance(final String accountId);
    CustomMoneyAmount withdraw(final String accountId, final CustomMoneyAmount customMoneyAmount);
    CustomMoneyAmount deposit(final String accountId, final CustomMoneyAmount customMoneyAmount);
}
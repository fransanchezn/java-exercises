package org.fransanchez.usecases.atm.bankservice;

import org.fransanchez.usecases.atm.MoneyAmount;

public interface BankingService {
    boolean validateUser(final String cardId, final String pinNumber);
    Account getAccount(final String cardId);
    MoneyAmount withdraw(final String accountId, final MoneyAmount moneyAmount);
    MoneyAmount deposit(final String accountId, final MoneyAmount moneyAmount);
}

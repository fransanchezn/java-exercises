package org.fransanchez.usecases.atm.bankservice;

import org.fransanchez.usecases.atm.CustomMoneyAmount;

public interface BankingService {
    boolean validateUser(final String cardId, final String pinNumber);
    Account getAccount(final String cardId);
    CustomMoneyAmount withdraw(final String accountId, final CustomMoneyAmount customMoneyAmount);
    CustomMoneyAmount deposit(final String accountId, final CustomMoneyAmount customMoneyAmount);
}

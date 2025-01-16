package org.fransanchez.usecases.atm;

import org.fransanchez.usecases.atm.bankservice.BankingService;

public class DefaultAtmService implements AtmService {
    private final BankingService bankService;

    public DefaultAtmService(final BankingService bankService) {
        this.bankService = bankService;
    }

    @Override
    public boolean validateUser(String cardId, String pinNumber) {
        return bankService.validateUser(cardId, pinNumber);
    }

    @Override
    public CustomMoneyAmount balance(final String accountId) {
        return bankService.getAccount(accountId).balance();
    }

    @Override
    public CustomMoneyAmount withdraw(String accountId, CustomMoneyAmount customMoneyAmount) {
        final var account = bankService.getAccount(accountId);
        synchronized (account) {
            return bankService.withdraw(accountId, customMoneyAmount);
        }
    }

    @Override
    public CustomMoneyAmount deposit(String accountId, CustomMoneyAmount customMoneyAmount) {
        final var account = bankService.getAccount(accountId);
        synchronized (account) {
            return bankService.deposit(accountId, customMoneyAmount);
        }
    }
}

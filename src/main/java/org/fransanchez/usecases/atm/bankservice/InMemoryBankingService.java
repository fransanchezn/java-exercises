package org.fransanchez.usecases.atm.bankservice;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import org.fransanchez.usecases.atm.MoneyAmount;

public class InMemoryBankingService implements BankingService {
    private static final Map<String, String> CARD_AUTHORIZATION = new HashMap<>();
    private static final Map<String, Account> ACCOUNTS = new HashMap<>();

    static {
        final var cardId = "cardId1";
        CARD_AUTHORIZATION.put(cardId, "1234");

        final var account1 = new Account(
                "accountId1",
                new MoneyAmount(
                        Currency.getInstance("EUR"),
                        new BigDecimal("10")
                )
        );
        final var account2 = new Account(
                "accountId2",
                new MoneyAmount(
                        Currency.getInstance("EUR"),
                        new BigDecimal("0")
                )
        );
        ACCOUNTS.put(account1.accountId(), account1);
        ACCOUNTS.put(account2.accountId(), account2);
    }

    @Override
    public boolean validateUser(final String cardId, final String pinNumber) {
        final var cardAuth = CARD_AUTHORIZATION.get(cardId);
        if (cardAuth == null) {
            throw new IllegalArgumentException("Card not for for id: " + cardId);
        }

        return cardAuth.equals(pinNumber);
    }

    @Override
    public Account getAccount(final String accountId) {
        final var account = ACCOUNTS.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not for for id: " + accountId);
        }

        return account;
    }

    @Override
    public MoneyAmount withdraw(final String accountId, final MoneyAmount moneyAmount) {
        final var account = getAccount(accountId);
        sleep(5_000L);
        return account.withdraw(moneyAmount);
    }

    @Override
    public MoneyAmount deposit(final String accountId, final MoneyAmount moneyAmount) {
        final var account = getAccount(accountId);
        sleep(3_000L);
        return account.deposit(moneyAmount);
    }

    private void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

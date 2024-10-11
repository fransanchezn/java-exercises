package org.fransanchez.usecases.atm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import org.fransanchez.usecases.atm.bankservice.BankingService;
import org.fransanchez.usecases.atm.bankservice.InMemoryBankingService;

public class AtmMain {
    public static void main(String[] args) throws InterruptedException {
        final var executor = Executors.newFixedThreadPool(10);

        final BankingService bankingService = new InMemoryBankingService();
        final AtmService atmService = new DefaultAtmService(bankingService);

        final var cardId = "cardId1";
        final var accountId1 = "accountId1";
        final var accountId2 = "accountId2";

        final var validated = atmService.validateUser(cardId, "1234");
        System.out.println("Card id: " + cardId + " is validated: " + validated);

        System.out.println("The balance for accountId: " + accountId1 + " is: " + atmService.balance(accountId1));
        System.out.println("The balance for accountId: " + accountId2 + " is: " + atmService.balance(accountId2));

        final var callables = new ArrayList<Callable<Void>>();
        callables.add(() -> {
            final var depositBalance = atmService.deposit(accountId1, new MoneyAmount(Currency.getInstance("EUR"), new BigDecimal("15.55")));
            System.out.println("[deposit] The balance for accountId: " + accountId1 + " is: " + depositBalance);
            return null;
        });

        callables.add(() -> {
            final var depositBalance = atmService.deposit(accountId2, new MoneyAmount(Currency.getInstance("EUR"), new BigDecimal("5.55")));
            System.out.println("[deposit] The balance for accountId: " + accountId2 + " is: " + depositBalance);
            return null;
        });

        callables.add(() -> {
            final var withdrawBalance = atmService.withdraw(accountId1, new MoneyAmount(Currency.getInstance("EUR"), new BigDecimal("5.55")));
            System.out.println("[withdraw] The balance for accountId: " + accountId1 + " is: " + withdrawBalance);
            return null;
        });

        callables.add(() -> {
            final var withdrawBalance = atmService.withdraw(accountId2, new MoneyAmount(Currency.getInstance("EUR"), new BigDecimal("5.55")));
            System.out.println("[withdraw] The balance for accountId: " + accountId2 + " is: " + withdrawBalance);
            return null;
        });

        executor.invokeAll(callables);

        executor.shutdown();
    }
}

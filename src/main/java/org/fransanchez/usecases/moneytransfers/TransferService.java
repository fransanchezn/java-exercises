package org.fransanchez.usecases.moneytransfers;

import org.fransanchez.usecases.moneytransfers.infrastructure.InMemAccountRepository;
import org.fransanchez.usecases.moneytransfers.infrastructure.InMemTransferRepository;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class TransferService {
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    // These locks would be slower due to locking for all entry instead of specific entries!
    private final Object lock1;
    private final Object lock2;

    public TransferService(final AccountRepository accountRepository, final TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;

        // These locks would be slower due to locking for all entry instead of specific entries!
        this.lock1 = new Object();
        this.lock2 = new Object();
    }

    public Transfer createTransfer(final UUID fromAccountId, final UUID toAccountId, final MonetaryAmount amount) throws InterruptedException {
        final var fromAccount = accountRepository.get(fromAccountId)
                .orElseThrow(() -> new RuntimeException("From account with Id: " + fromAccountId + " not found"));
        final var toAccount = accountRepository.get(toAccountId)
                .orElseThrow(() -> new RuntimeException("To account with Id: " + fromAccountId + " not found"));

        final var firstLock = fromAccount.accountId().compareTo(toAccount.accountId()) > 0 ? fromAccount : toAccount;
        final var secondLock = fromAccount.accountId().compareTo(toAccount.accountId()) <= 0 ? toAccount : fromAccount;

        synchronized (firstLock) {
            synchronized (secondLock) {
                Thread.sleep(500);
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            }
        }

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        final var transfer = new Transfer(fromAccount, toAccount, amount);
        transferRepository.insert(transfer);

        return transfer;
    }

    public static void main(final String[] args) {
        try (final var executor = Executors.newFixedThreadPool(100)) {
            final var accountRepository = new InMemAccountRepository();
            final var transferRepository = new InMemTransferRepository();
            final var transferService = new TransferService(accountRepository, transferRepository);
            final var account1 = accountRepository.save(new Account(Monetary.getCurrency("EUR")));
            final var account2 = accountRepository.save(new Account(Monetary.getCurrency("EUR")));
            final var account3 = accountRepository.save(new Account(Monetary.getCurrency("EUR")));
            final var account4 = accountRepository.save(new Account(Monetary.getCurrency("EUR")));

            final var runnables = new ArrayList<Callable<Transfer>>();
            for (int i = 0; i < 10; i++) {
                runnables.add(() -> transferService.createTransfer(account1.accountId(), account2.accountId(), Money.of(100, "EUR")));
            }

            for (int i = 0; i < 10; i++) {
                runnables.add(() -> transferService.createTransfer(account2.accountId(), account1.accountId(), Money.of(100, "EUR")));
            }

            for (int i = 0; i < 10; i++) {
                runnables.add(() -> transferService.createTransfer(account3.accountId(), account4.accountId(), Money.of(100, "EUR")));
            }

            for (int i = 0; i < 10; i++) {
                runnables.add(() -> transferService.createTransfer(account4.accountId(), account3.accountId(), Money.of(100, "EUR")));
            }

            final var start = System.currentTimeMillis();
            final var transfersFutures = executor.invokeAll(runnables);

            final var transfers = transfersFutures.stream()
                    .map(i -> {
                        try {
                            return i.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toSet());

            System.out.println("Time: " + Duration.of(System.currentTimeMillis() - start, ChronoUnit.MILLIS).toSeconds());

            System.out.println(transfers);
            System.out.println(accountRepository.get(account1.accountId()).get());
            System.out.println(accountRepository.get(account2.accountId()).get());
            System.out.println(accountRepository.get(account3.accountId()).get());
            System.out.println(accountRepository.get(account4.accountId()).get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

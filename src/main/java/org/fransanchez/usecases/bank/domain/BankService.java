package org.fransanchez.usecases.bank.domain;

import org.fransanchez.usecases.bank.domain.account.Account;
import org.fransanchez.usecases.bank.domain.account.AccountRepository;
import org.fransanchez.usecases.bank.domain.transaction.DepositTransaction;
import org.fransanchez.usecases.bank.domain.transaction.OpenAccountTransaction;
import org.fransanchez.usecases.bank.domain.transaction.Transaction;
import org.fransanchez.usecases.bank.domain.transaction.TransactionRepository;
import org.fransanchez.usecases.bank.domain.transaction.WithdrawTransaction;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.UUID;

public class BankService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(final AccountRepository accountRepository, final TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account openAccount(final String customerId, final String employeeId) {
        final var account = new Account(UUID.randomUUID().toString(), customerId, Money.of(0, "EUR"));
        final var savedAccount = accountRepository.create(account);

        // Transaction record logged
        final var transaction = new OpenAccountTransaction(account.accountId(), employeeId, customerId);
        transactionRepository.save(transaction);

        return savedAccount;
    }

    public Transaction deposit(final String accountId, final String employeeId, MonetaryAmount amount) {
        final var accountOpt = accountRepository.getById(accountId);
        if (accountOpt.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        final var account = accountOpt.get();
        account.deposit(amount);

        final var transaction = new DepositTransaction(accountId, employeeId, amount);
        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(final String accountId, final String employeeId, MonetaryAmount amount) {
        final var accountOpt = accountRepository.getById(accountId);
        if (accountOpt.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        final var account = accountOpt.get();
        account.withdraw(amount);

        final var transaction = new WithdrawTransaction(accountId, employeeId, amount);
        return transactionRepository.save(transaction);
    }
}

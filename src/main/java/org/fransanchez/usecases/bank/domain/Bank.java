package org.fransanchez.usecases.bank.domain;

import org.fransanchez.usecases.bank.infrastructure.account.InMemAccountRepository;
import org.fransanchez.usecases.bank.infrastructure.transaction.InMemTransactionRepository;
import org.javamoney.moneta.Money;

import java.util.List;

public class Bank {
    private final List<BankBranch> branches;

    public Bank(final List<BankBranch> branches) {
        this.branches = branches;
    }

    public static void main(String[] args) {
        final var accountRepository = new InMemAccountRepository();
        final var transactionRepository = new InMemTransactionRepository();
        final var bankService = new BankService(accountRepository, transactionRepository);

        final var employee1 = new Employee("1");
        final var employee2 = new Employee("2");
        final var bankBranch1 = new BankBranch(bankService, List.of(employee1, employee2));


        final var account = bankBranch1.openAccount("1");
        bankBranch1.deposit(account.accountId(), Money.of(10_000, "EUR"));
        bankBranch1.withdraw(account.accountId(), Money.of(9000, "EUR"));
    }
}

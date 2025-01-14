package org.fransanchez.usecases.bank.domain;

import org.fransanchez.usecases.bank.domain.account.Account;
import org.fransanchez.usecases.bank.domain.transaction.Transaction;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.List;

public class BankBranch {
    private final BankService bankService;
    private final List<Employee> employees;
    private MonetaryAmount balance;

    public BankBranch(final BankService bankService, final List<Employee> employees) {
        this.bankService = bankService;
        this.employees = employees;
        this.balance = Money.of(1_000, "EUR");
    }

    public Account openAccount(final String customerId) {
        final var employee = getEmployee();
        return bankService.openAccount(customerId, employee.id());
    }

    public Transaction deposit(final String accountId, final MonetaryAmount amount) {
        final var employee = getEmployee();
        balance = balance.add(amount);
        return bankService.deposit(accountId, employee.id(), amount);
    }

    public Transaction withdraw(final String accountId, final MonetaryAmount amount) {
        final var employee = getEmployee();
        balance = balance.subtract(amount);
        return bankService.withdraw(accountId, employee.id(), amount);
    }

    private Employee getEmployee() {
        int index = (int) Math.round(Math.random() * (this.employees.size() - 1));
        return this.employees.get(index);
    }
}

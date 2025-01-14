package org.fransanchez.usecases.bank.infrastructure.account;

import org.fransanchez.usecases.bank.domain.account.Account;
import org.fransanchez.usecases.bank.domain.account.AccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemAccountRepository implements AccountRepository {
    private final Map<String, Account> accounts;

    public InMemAccountRepository() {
        this.accounts = new HashMap<>();
    }

    @Override
    public Optional<Account> getById(String accountId) {
        return Optional.ofNullable(accounts.get(accountId));
    }

    @Override
    public Account create(Account account) {
        accounts.put(account.accountId(), account);
        return account;
    }
}

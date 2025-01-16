package org.fransanchez.usecases.moneytransfers.infrastructure;

import org.fransanchez.usecases.moneytransfers.Account;
import org.fransanchez.usecases.moneytransfers.AccountRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemAccountRepository implements AccountRepository {
    private final Map<UUID, Account> accounts;

    public InMemAccountRepository() {
        this.accounts = new ConcurrentHashMap<>();
    }

    @Override
    public Account save(final Account account) {
        accounts.put(account.accountId(), account);
        return account;
    }

    @Override
    public Optional<Account> get(final UUID accountId) {
        return Optional.ofNullable(accounts.get(accountId));
    }
}

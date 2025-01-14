package org.fransanchez.usecases.bank.domain.account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> getById(final String accountId);
    Account create(final Account account);
}

package org.fransanchez.usecases.moneytransfers;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(final Account account);
    Optional<Account> get(final UUID accountId);
}

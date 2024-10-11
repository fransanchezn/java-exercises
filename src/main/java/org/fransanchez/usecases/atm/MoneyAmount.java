package org.fransanchez.usecases.atm;

import java.math.BigDecimal;
import java.util.Currency;

public record MoneyAmount(Currency currency, BigDecimal amount) { }

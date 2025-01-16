package org.fransanchez.usecases.atm;

import java.math.BigDecimal;
import java.util.Currency;

public record CustomMoneyAmount(Currency currency, BigDecimal amount) { }

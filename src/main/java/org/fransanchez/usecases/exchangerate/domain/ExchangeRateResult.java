package org.fransanchez.usecases.exchangerate.domain;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

public record ExchangeRateResult(MonetaryAmount amount, BigDecimal rate) {}

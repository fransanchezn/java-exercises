package org.fransanchez.usecases.exchangerate.domain;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

public record MoneyConverterResult(MonetaryAmount amount, BigDecimal rate) {}

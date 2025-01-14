package org.fransanchez.usecases.exchangerate.infrastructure.openexchangerate;

import java.util.Map;

public record OpenExchangeRateResponse(String base, Map<String, String> rates) {
}


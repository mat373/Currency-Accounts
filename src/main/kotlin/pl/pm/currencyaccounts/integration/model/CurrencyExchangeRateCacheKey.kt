package pl.pm.currencyaccounts.integration.model

import pl.pm.currencyaccounts.core.enum.Currency
import java.time.LocalDate

internal data class CurrencyExchangeRateCacheKey(
    val date: LocalDate,
    val currency: Currency
)
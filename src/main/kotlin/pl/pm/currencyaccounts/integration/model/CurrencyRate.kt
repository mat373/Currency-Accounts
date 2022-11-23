package pl.pm.currencyaccounts.integration.model

import java.math.BigDecimal
import java.time.LocalDate

data class CurrencyRate(
    val effectiveDate: LocalDate,
    val bid: BigDecimal,
    val ask: BigDecimal
)

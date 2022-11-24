package pl.pm.currencyaccounts.exchange.model

import pl.pm.currencyaccounts.core.enum.Currency
import java.math.BigDecimal

data class Exchange (
    val accountId: Long,
    val currency: Currency,
    val amount: BigDecimal
)
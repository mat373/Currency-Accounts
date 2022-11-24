package pl.pm.currencyaccounts.exchange.model

import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.core.util.PersonalId
import java.math.BigDecimal

/***
 * @param amountFrom - given amount in currencyFrom
 */
data class Exchange (
    val personalId: PersonalId,
    val currencyFrom: Currency,
    val amountFrom: BigDecimal,
    val currencyTo: Currency
)
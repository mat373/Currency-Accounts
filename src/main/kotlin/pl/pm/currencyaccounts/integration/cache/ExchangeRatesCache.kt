package pl.pm.currencyaccounts.integration.cache

import arrow.core.memoize
import org.springframework.stereotype.Component
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.integration.api.ExchangeRatesClient
import java.time.LocalDate
@Component
internal class ExchangeRatesCache(
    private val client: ExchangeRatesClient
) {
    private val cache = client::getExchangeRates.memoize()
    fun getActualRate(currency: Currency) = cache(currency, LocalDate.now())
}
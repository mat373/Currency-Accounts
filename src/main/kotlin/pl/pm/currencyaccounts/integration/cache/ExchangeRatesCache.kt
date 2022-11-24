package pl.pm.currencyaccounts.integration.cache

import arrow.core.memoize
import org.springframework.stereotype.Component
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.integration.api.ExchangeRatesClient
import pl.pm.currencyaccounts.integration.model.CurrencyExchangeRateCacheKey
import pl.pm.currencyaccounts.integration.model.CurrencyRate
import java.time.LocalDate
import java.util.concurrent.ConcurrentHashMap

@Component
internal class ExchangeRatesCache(
    private val client: ExchangeRatesClient
) {

    private val cache = client::getExchangeRates.memoize()
    fun findActualMono(currency: Currency) = cache(currency, LocalDate.now())
}
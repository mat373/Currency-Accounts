package pl.pm.currencyaccounts.integration.cache

import org.springframework.stereotype.Component
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.integration.model.CurrencyExchangeRateCacheKey
import pl.pm.currencyaccounts.integration.model.CurrencyRate
import java.util.concurrent.ConcurrentHashMap

@Component
internal class ExchangeRatesCache {

    private val cache = ConcurrentHashMap<CurrencyExchangeRateCacheKey, Pair<CurrencyRate, Currency>>()

    fun put(exchangeRate: Pair<CurrencyRate, Currency>) =
        cache.getOrPut(
            CurrencyExchangeRateCacheKey(
               exchangeRate.first.effectiveDate,
                exchangeRate.second
            )
        ) { exchangeRate }

    fun findActual(currency: Currency) =
        find { this.currency == currency }
            .minByOrNull { it.second }

    private fun find(predicate: CurrencyExchangeRateCacheKey.() -> Boolean) =
        cache
            .entries
            .filter { predicate(it.key) }
            .map { it.value }
}
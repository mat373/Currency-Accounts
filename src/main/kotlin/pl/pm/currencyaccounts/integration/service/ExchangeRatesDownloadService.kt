package pl.pm.currencyaccounts.integration.service

import org.springframework.stereotype.Component
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.integration.cache.ExchangeRatesCache
import pl.pm.currencyaccounts.integration.model.CurrencyRate

interface ExchangeRatesService {
    fun getCurrencyExchangeRate(currency: Currency): Pair<CurrencyRate, Currency>?
}

@Component
internal class ExchangeRatesClientService(private val cache: ExchangeRatesCache) : ExchangeRatesService {

    override fun getCurrencyExchangeRate(currency: Currency) =
        cache.findActual(currency)
}
